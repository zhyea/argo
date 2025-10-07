package com.zhyea.argo.model.request;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * 更新用户请求
 *
 * @author robin
 */
@Data
public class UserUpdateRequest implements Serializable {


	private static final long serialVersionUID = -8340928573490182345L;


	/**
	 * 用户ID
	 */
	@NotNull(message = "用户ID不可为空")
	private Long id;


	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不可为空")
	private String username;


	/**
	 * 昵称
	 */
	@NotBlank(message = "昵称不可为空")
	private String nickname;


}