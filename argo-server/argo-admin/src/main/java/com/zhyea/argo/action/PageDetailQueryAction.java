package com.zhyea.argo.action;

import com.zhyea.argo.cms.model.item.FciItem;
import com.zhyea.argo.cms.model.item.FciPropItem;
import com.zhyea.argo.cms.model.item.PageItem;
import com.zhyea.argo.cms.service.FciPropService;
import com.zhyea.argo.cms.service.FciService;
import com.zhyea.argo.cms.service.PageService;
import com.zhyea.argo.constants.enums.HttpQueryMethodEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import com.zhyea.argo.except.ArgoServerException;
import com.zhyea.argo.model.item.FciDetailItem;
import com.zhyea.argo.model.item.FciPropSimpleItem;
import com.zhyea.argo.model.item.PageDetailItem;
import com.zhyea.argo.model.request.PageDetailRequest;
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


	private final PageService pageService;
	private final FciService fciService;
	private final FciPropService fciPropService;

	@Autowired
	public PageDetailQueryAction(PageService pageService, FciService fciService, FciPropService fciPropService) {
		this.pageService = pageService;
		this.fciService = fciService;
		this.fciPropService = fciPropService;
	}


	public PageDetailItem query(PageDetailRequest request) {
		PageItem pageItem = pageService.getByPageCode(request.getPageCode());
		// 使用CompletableFuture获取到全部的组件及属性信息


		// 聚合接口，获取组件属性信息

		return null;
	}


	private FciDetailItem queryFciDetail(Long fciId) {
		FciItem fciItem = fciService.getById(fciId);
		List<FciPropItem> fciProps = fciPropService.findValidByFciId(fciId);


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
			HttpReqInfo reqInfo = new HttpReqInfo(req.getUrl(), method);

			List<FciPropItem> list = map.computeIfAbsent(reqInfo, k -> new LinkedList<>());
			list.add(e);
		}

		// 查询接口，获取数据



		return result;
	}


	private record HttpReqInfo(String url, HttpQueryMethodEnum method) {
	}


}
