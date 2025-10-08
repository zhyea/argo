package com.zhyea.argo.controller;

import com.zhyea.argo.action.UserLoginAction;
import com.zhyea.argo.cms.annotation.OperationLog;
import com.zhyea.argo.model.request.UserLoginRequest;
import com.zhyea.argo.tools.auth.AuthContext;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("")
public class AuthController {


	private final UserLoginAction loginAction;

	public AuthController(UserLoginAction loginAction) {
		this.loginAction = loginAction;
	}


	@OperationLog(type = "用户认证", description = "用户登录", recordParams = false, recordResult = false)
	@PostMapping("/login")
	public String login(@RequestBody @Validated UserLoginRequest request) throws Exception {
		return loginAction.doLogin(request.getUsername(), request.getPassword());
	}


	@OperationLog(type = "用户认证", description = "用户登出", recordParams = false, recordResult = false)
	@PostMapping("/logout")
	public boolean logout() {
		AuthContext.clear();
		return true;
	}


	@RequestMapping("/ping")
	public String ping() {
		return "Medea";
	}


}
