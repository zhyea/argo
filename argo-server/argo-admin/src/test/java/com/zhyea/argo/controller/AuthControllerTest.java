package com.zhyea.argo.controller;

import com.zhyea.argo.ApiTestBase;
import com.zhyea.argo.model.request.UserLoginRequest;
import org.junit.jupiter.api.Test;

public class AuthControllerTest extends ApiTestBase {


	@Override
	protected String parentPath() {
		return "";
	}


	@Test
	public void login() {
		UserLoginRequest request = new UserLoginRequest();

		String path = "/login";
		String result = testPost(path, request, String.class);
		System.out.println(result);
	}

}
