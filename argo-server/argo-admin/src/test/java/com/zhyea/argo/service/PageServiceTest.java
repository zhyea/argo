package com.zhyea.argo.service;

import com.zhyea.argo.TestBase;
import com.zhyea.argo.cms.model.request.page.PageAddRequest;
import com.zhyea.argo.cms.service.PageService;
import org.chobit.commons.tools.ShortCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.chobit.commons.utils.StrKit.uuid;

public class PageServiceTest extends TestBase {


	@Autowired
	private PageService pageService;


	@Test
	public void add(){
		PageAddRequest request = new PageAddRequest();
		request.setAppId(1L);
		request.setPageCode(ShortCode.genUpper());
		request.setPageName(uuid());

		Long id = pageService.add(request);
		Assertions.assertNotNull(id);
	}

}
