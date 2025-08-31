package com.zhyea.argo.cms.action;

import com.zhyea.argo.cms.model.item.FciItem;
import com.zhyea.argo.cms.model.item.PageFciAssembleItem;
import com.zhyea.argo.cms.service.PageService;
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


	public PageFciAssembleItem pageFciList(Long appId, Long pageId) {
		PageFciAssembleItem result = new PageFciAssembleItem();
		List<FciItem> availableFciList = pageService.findAvailableFci(appId, pageId);
		List<FciItem> usedFciList = pageService.findByPageId(pageId);

		result.setAvailableFciList(availableFciList);
		result.setUsedFciList(usedFciList);

		return result;
	}


}
