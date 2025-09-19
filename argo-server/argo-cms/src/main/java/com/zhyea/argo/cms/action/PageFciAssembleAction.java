package com.zhyea.argo.cms.action;

import com.zhyea.argo.model.item.FciItem;
import com.zhyea.argo.model.item.PageFciAssembleItem;
import com.zhyea.argo.model.item.PageItem;
import com.zhyea.argo.biz.service.PageService;
import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.except.ArgoServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 页面组件组装业务逻辑
 *
 * @author robin
 * @since 2025/8/31 23:47
 */
@Component
public class PageFciAssembleAction {


	private final PageService pageService;


	@Autowired
	public PageFciAssembleAction(PageService pageService) {
		this.pageService = pageService;
	}


	public PageFciAssembleItem pageFciList(Long pageId) {
		PageItem page = pageService.get(pageId);
		if (null == page) {
			throw new ArgoServerException(ResponseCode.PAGE_NOT_EXISTS_ERROR);
		}

		PageFciAssembleItem result = new PageFciAssembleItem();
		List<FciItem> availableFciList = pageService.findAvailableFci(page.getAppId(), pageId);
		List<FciItem> usedFciList = pageService.findByPageId(pageId);

		result.setAvailableFciList(availableFciList);
		result.setEmbeddedFciList(usedFciList);

		return result;
	}


}
