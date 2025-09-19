package com.zhyea.argo.api.action;

import com.zhyea.argo.api.model.item.FciDetailItem;
import com.zhyea.argo.api.model.item.FciPropSimpleItem;
import com.zhyea.argo.api.model.item.PageDetailItem;
import com.zhyea.argo.api.model.request.PageDetailRequest;
import com.zhyea.argo.constants.enums.HttpQueryMethodEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import com.zhyea.argo.data.mapper.cms.PageMapper;
import com.zhyea.argo.except.ArgoServerException;
import com.zhyea.argo.model.item.FciItem;
import com.zhyea.argo.model.item.FciPropItem;
import com.zhyea.argo.model.item.PageItem;
import com.zhyea.argo.tools.ParallelKit;
import org.chobit.commons.utils.UrlKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.zhyea.argo.constants.ResponseCode.ILLEGAL_HTTP_METHOD_ERROR;

/**
 * 页面信息查询
 *
 * @author robin
 * @since 2025/9/3 23:21
 */
@Component
public class PageDetailQueryAction {


	private final PageMapper pageMapper;
	private final FciService fciService;
	private final FciPropService fciPropService;

	@Autowired
	public PageDetailQueryAction(PageService pageService, FciService fciService, FciPropService fciPropService) {
		this.pageMapper = pageService;
		this.fciService = fciService;
		this.fciPropService = fciPropService;
	}


	public PageDetailItem query(PageDetailRequest request) {
		PageItem pageItem = pageMapper.getByPageCode(request.getPageCode());
		// 使用CompletableFuture获取到全部的组件及属性信息


		// 聚合接口，获取组件属性信息

		return null;
	}


	private FciDetailItem queryFciDetail(Long fciId) {

		List<Object> result = ParallelKit.executeIgnoreErrors(() -> fciService.getById(fciId),
				() -> fciPropService.findEffectivePropsByFciId(fciId),
				() -> pageMapper.findFciUsage(fciId));

		FciItem fciItem = null;
		List<FciPropItem> fciProps = null;
		List<PageItem> pageItems = null;

		if (null != result.get(0)) {
			fciItem = (FciItem) result.get(0);
		}
		if (null != result.get(1)) {
			fciProps = (List<FciPropItem>) result.get(1);
		}
		if (null != result.get(2)) {
			pageItems = (List<PageItem>) result.get(2);
		}

		FciDetailItem fciDetailItem = new FciDetailItem();
		return fciDetailItem;
	}


	private List<FciPropSimpleItem> tidyFciProps(List<FciPropItem> origenPropItems) {
		List<FciPropSimpleItem> result = new LinkedList<>();

		// 聚合接口，获取组件属性信息
		Map<HttpReqInfo, List<FciPropItem>> map = new HashMap<>(8);

		for (FciPropItem e : origenPropItems) {
			// 提取直接属性
			if (YesOrNo.YES.is(e.getDataBindFlag())) {
				result.add(new FciPropSimpleItem(e.getPropKey(), e.getPropValue()));
				continue;
			}

			// 整理需要查询接口的属性
			HttpQueryMethodEnum method = HttpQueryMethodEnum.codeOf(e.getDataRequestMethod());

			if (null == method) {
				throw new ArgoServerException(ILLEGAL_HTTP_METHOD_ERROR);
			}

			UrlKit.GetReq req = UrlKit.parse(e.getDataUrl());
			HttpReqInfo reqInfo = new HttpReqInfo(req.getUrl(), method, req.getParams());

			List<FciPropItem> list = map.computeIfAbsent(reqInfo, k -> new LinkedList<>());
			list.add(e);
		}

		// 查询接口，获取数据


		return result;
	}


	private List<FciPropSimpleItem> queryForProps(HttpReqInfo reqInfo, List<FciPropItem> propInfoList, Map<String, Object> srcHeaders) {

		Map<String, Object> newHeaders = new HashMap<>(8);
		Map<String, Object> newParams = new HashMap<>(8);

		for (FciPropItem p : propInfoList) {

		}


		return null;
	}


}


record HttpReqInfo(String url, HttpQueryMethodEnum method, Map<String, String> params) {
}