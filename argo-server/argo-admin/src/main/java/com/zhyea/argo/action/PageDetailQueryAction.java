package com.zhyea.argo.action;

import com.zhyea.argo.cms.model.item.PageItem;
import com.zhyea.argo.cms.service.FciPropService;
import com.zhyea.argo.cms.service.FciService;
import com.zhyea.argo.cms.service.PageService;
import com.zhyea.argo.model.item.PageDetailItem;
import com.zhyea.argo.model.request.PageDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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



		return null;
	}


}
