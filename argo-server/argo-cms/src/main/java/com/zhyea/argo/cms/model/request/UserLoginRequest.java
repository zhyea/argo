package com.zhyea.argo.cms.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录请求
 *
 * @author robin
 */
@Data
public class UserLoginRequest {


	@NotBlank(message = "用户名不可为空")
	private String username;


	@NotBlank(message = "密码不可为空")
	private String password;


}
