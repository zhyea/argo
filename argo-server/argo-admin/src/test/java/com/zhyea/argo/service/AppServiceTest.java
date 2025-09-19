package com.zhyea.argo.service;

import com.zhyea.argo.TestBase;
import com.zhyea.argo.model.request.app.AppAddRequest;
import com.zhyea.argo.biz.service.AppService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AppServiceTest extends TestBase {


	@Autowired
	private AppService appService;


	@Test
	public void add() {
		AppAddRequest req = new AppAddRequest();
		req.setAppCode("test2");
		req.setAppName("测试应用");
		req.setRemark("测试应用");
		req.setIcon("icon");

		Long id = appService.add(req);
		Assertions.assertNotNull(id);
	}

}
