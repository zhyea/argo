package com.zhyea.argo.service;

import com.zhyea.argo.TestBase;
import com.zhyea.argo.cms.model.request.app.AppPageAddRequest;
import com.zhyea.argo.cms.service.AppPageService;
import org.chobit.commons.tools.ShortCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.chobit.commons.utils.StrKit.uuid;

public class AppPageServiceTest extends TestBase {


	@Autowired
	private AppPageService appPageService;


	@Test
	public void add(){
		AppPageAddRequest request = new AppPageAddRequest();
		request.setAppId(1L);
		request.setPageCode(ShortCode.genUpper());
		request.setPageName(uuid());

		Long id = appPageService.add(request);
		Assertions.assertNotNull(id);
	}

}
