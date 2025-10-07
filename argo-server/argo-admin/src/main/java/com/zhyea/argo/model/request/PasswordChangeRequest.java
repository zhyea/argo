package com.zhyea.argo.model.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * 修改密码请求
 *
 * @author robin
 */
@Data
public class PasswordChangeRequest implements Serializable {


	private static final long serialVersionUID = 1234567890987654321L;


	/**
	 * 用户ID
	 */
	@NotNull(message = "用户ID不可为空")
	private Long id;

	/**
	 * 旧密码
	 */
	@NotBlank(message = "旧密码不可为空")
	private String oldPassword;

	/**
	 * 新密码
	 */
	@NotBlank(message = "新密码不可为空")
	private String newPassword;

	/**
	 * 确认新密码
	 */
	@NotBlank(message = "确认新密码不可为空")
	private String confirmPassword;

}