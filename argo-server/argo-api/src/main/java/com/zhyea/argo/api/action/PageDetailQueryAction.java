package com.zhyea.argo.api.action;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.zhyea.argo.api.model.item.FciDetailItem;
import com.zhyea.argo.api.model.item.FciPropSimpleItem;
import com.zhyea.argo.api.model.item.PageDetailItem;
import com.zhyea.argo.api.model.request.PageDetailRequest;
import com.zhyea.argo.api.tools.ServletRequestKit;
import com.zhyea.argo.biz.service.FciPropService;
import com.zhyea.argo.biz.service.FciService;
import com.zhyea.argo.biz.service.PageService;
import com.zhyea.argo.constants.enums.DataBindFlagEnum;
import com.zhyea.argo.constants.enums.RequestQueryMethodEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import com.zhyea.argo.except.ArgoServerException;
import com.zhyea.argo.model.item.BaseDataBindRequest;
import com.zhyea.argo.model.item.FciItem;
import com.zhyea.argo.model.item.FciPropItem;
import com.zhyea.argo.model.item.PageItem;
import com.zhyea.argo.tools.ParallelKit;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.constans.Symbol;
import org.chobit.commons.http.HttpClient;
import org.chobit.commons.http.HttpResult;
import org.chobit.commons.utils.Collections2;
import org.chobit.commons.utils.JsonKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Supplier;

import static com.zhyea.argo.constants.ResponseCode.*;
import static org.chobit.commons.utils.StrKit.isBlank;

/**
 * 页面信息查询
 *
 * @author robin
 * @since 2025/9/3 23:21
 */
@Slf4j
@Component
public class PageDetailQueryAction {


	private final PageService pageService;
	private final FciService fciService;
	private final FciPropService fciPropService;

	@Autowired
	public PageDetailQueryAction(PageService pageService, FciService fciService, FciPropService fciPropService) {
		this.pageService = pageService;
		this.fciService = fciService;
		this.fciPropService = fciPropService;
	}


	public PageDetailItem query(PageDetailRequest request, HttpServletRequest httpReq) {
		PageDetailItem result = new PageDetailItem();

		PageItem pageItem = pageService.getByPageCode(request.getPageCode());
		if (null == pageItem) {
			throw new ArgoServerException(PAGE_NOT_EXISTS_ERROR);
		}
		result.setPage(pageItem);

		// 使用CompletableFuture获取到全部的组件及属性信息
		List<FciItem> fciList = pageService.findAvailableFci(pageItem.getAppId(), pageItem.getPageId());
		if (Collections2.isEmpty(fciList)) {
			return result;
		}

		// 聚合接口，获取组件属性信息
		List<Supplier<FciDetailItem>> supplierList = new ArrayList<>(fciList.size());
		fciList.forEach(e -> supplierList.add(() -> queryFciDetail(e.getId(), httpReq)));
		List<FciDetailItem> fciDetailList = ParallelKit.executeIgnoreErrors(supplierList, ParallelKit.BACKUP_EXECUTOR);
		result.setFciList(fciDetailList);

		return result;
	}


	private FciDetailItem queryFciDetail(Long fciId, HttpServletRequest httpReq) {

		List<Object> result = ParallelKit.executeIgnoreErrors(() -> fciService.getById(fciId),
				() -> fciPropService.findEffectivePropsByFciId(fciId),
				() -> pageService.findFciUsage(fciId));

		FciDetailItem detail = new FciDetailItem();

		if (Collections2.isEmpty(result)) {
			throw new ArgoServerException(FCI_NOT_EXISTS_ERROR);
		}

		FciItem fciItem = (FciItem) result.get(0);
		detail.setFci(fciItem);

		if (result.size() > 1 && null != result.get(1)) {
			List<FciPropItem> fciProps = (List<FciPropItem>) result.get(1);
			List<FciPropSimpleItem> tidiedFciProps = tidyFciProps(fciItem, fciProps, httpReq);
			detail.setFciPropList(tidiedFciProps);
		}

		return detail;
	}


	private List<FciPropSimpleItem> tidyFciProps(FciItem fciItem, List<FciPropItem> origenPropItems, HttpServletRequest httpReq) {
		List<FciPropSimpleItem> result = new LinkedList<>();

		HttpReqInfo parentReqInfo = null;
		if (YesOrNo.YES.is(fciItem.getDataBindFlag())) {
			parentReqInfo = parseHttpReqInfo(fciItem, httpReq);
		}

		// 聚合接口，获取组件属性信息
		Map<HttpReqInfo, List<FciPropItem>> map = new HashMap<>(8);

		for (FciPropItem e : origenPropItems) {
			if (DataBindFlagEnum.NOT_BIND.is(e.getDataBindFlag())) {
				// 提取直接属性
				result.add(new FciPropSimpleItem(e.getPropKey(), e.getPropValue()));
				continue;
			}

			HttpReqInfo reqInfo = parentReqInfo;
			if (DataBindFlagEnum.BIND_DATA.is(e.getDataBindFlag())) {
				reqInfo = parseHttpReqInfo(e, httpReq);
			}

			List<FciPropItem> list = map.computeIfAbsent(reqInfo, k -> new LinkedList<>());
			list.add(e);
		}

		if (!map.isEmpty()) {
			// 查询接口，获取数据
			List<Supplier<List<FciPropSimpleItem>>> supplierList = new ArrayList<>(map.size());
			map.forEach((key, value) -> supplierList.add(() -> this.queryForProps(key, value)));
			List<List<FciPropSimpleItem>> fciPropList = ParallelKit.executeIgnoreErrors(supplierList);
			fciPropList.forEach(result::addAll);
		}

		return result;
	}


	private HttpReqInfo parseHttpReqInfo(BaseDataBindRequest req, HttpServletRequest httpReq) {

		Map<String, String> srcParams = ServletRequestKit.getParameters(httpReq);
		Map<String, String> srcHeaders = ServletRequestKit.getHeaders(httpReq);

		String url = req.getDataUrl();
		RequestQueryMethodEnum method = RequestQueryMethodEnum.codeOf(req.getDataRequestMethod());
		Map<String, String> headers = takeHeaders(req.getDataRequestHeaders(), srcHeaders);
		Map<String, Object> params = parseParams(req.getId(), req.getDataRequestParams(), srcParams);

		return new HttpReqInfo(url, method, headers, params);
	}


	private Map<String, String> takeHeaders(String headersConfig, Map<String, String> srcHeaders) {
		Map<String, String> result = new HashMap<>(8);
		if (isBlank(headersConfig)) {
			return result;
		}
		String[] arr = headersConfig.split(Symbol.COMMA);
		for (String s : arr) {
			s = s.trim();
			if (s.contains(Symbol.COLON)) {
				String[] kv = s.split(Symbol.COLON);
				result.put(kv[0].trim(), kv[1].trim());
			} else {
				result.put(s, srcHeaders.get(s));
			}
		}
		return result;
	}


	private Map<String, Object> parseParams(Long propId, String paramsConfig, Map<String, String> srcParams) {

		Map<String, Object> result = new HashMap<>(8);

		if (isBlank(paramsConfig)) {
			return result;
		}

		String[] arr = paramsConfig.split(Symbol.AND);
		for (String s : arr) {
			String[] kv = s.split(Symbol.EQUAL);
			if (kv.length < 2) {
				logger.error("PageDetailQueryAction-parseParams propId:{}, 参数配置错误:{}", s, propId);
				continue;
			}

			String key = kv[0].trim();
			String value = kv[1].trim();

			if (!value.contains("{")) {
				// 常量，直接返回
				result.put(key, value);
			} else if (value.startsWith("${") && value.endsWith("}")) {
				// 变量，从请求参数中获取
				String p = value.substring(1, value.length() - 1);
				result.put(key, srcParams.get(p));
			} else if (value.startsWith("#{") && value.endsWith("}")) {
				// 常量的另一种表达方式，需要提取常量值
				String p = value.substring(1, value.length() - 1);
				result.put(key, p);
			} else {
				logger.error("PageDetailQueryAction-parseParams propId:{}, 无法识别的配置: {}", propId, s);
			}
		}

		return result;
	}


	private List<FciPropSimpleItem> queryForProps(HttpReqInfo reqInfo, List<FciPropItem> propInfoList) {
		HttpResult queryResult = null;

		if (RequestQueryMethodEnum.GET == reqInfo.method()) {
			queryResult = HttpClient.get(reqInfo.url(), reqInfo.headers(), reqInfo.params());
		} else {
			queryResult = HttpClient.postBody(reqInfo.url(), reqInfo.headers(), JsonKit.toJson(reqInfo.params()));
		}

		if (!queryResult.isSuccess() || isBlank(queryResult.getContent())) {
			throw new ArgoServerException(FCI_PROP_QUERY_ERROR);
		}

		ReadContext ctx = JsonPath.parse(queryResult.getContent());

		List<FciPropSimpleItem> result = new LinkedList<>();
		for (FciPropItem p : propInfoList) {
			String propValue = ctx.read(p.getPropValueSelector());
			result.add(new FciPropSimpleItem(p.getPropKey(), propValue));
		}

		return result;
	}
}


record HttpReqInfo(String url, RequestQueryMethodEnum method, Map<String, String> headers, Map<String, Object> params) {
}