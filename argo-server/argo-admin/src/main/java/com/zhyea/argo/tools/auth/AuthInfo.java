package com.zhyea.argo.tools.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限信息
 *
 * @author robin
 * @since 2025/4/18 23:00
 */
@NoArgsConstructor
@Data
public class AuthInfo {


	@JsonProperty("u")
	private String username;


	@JsonProperty("p")
	private String password;


	@JsonProperty("e")
	private Long expireTime;


	public AuthInfo(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
