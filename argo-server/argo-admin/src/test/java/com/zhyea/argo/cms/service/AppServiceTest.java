package com.zhyea.argo.cms.service;

import com.zhyea.argo.TestBase;
import com.zhyea.argo.cms.model.request.app.AppAddRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AppServiceTest extends TestBase {


	@Autowired
	private AppService appService;


	@Test
	public void add() {
		AppAddRequest req = new AppAddRequest();
		req.setAppCode("test");
		req.setAppName("测试应用");
		req.setRemark("测试应用");
		req.setIcon("icon");

		appService.add(req);
	}

}
