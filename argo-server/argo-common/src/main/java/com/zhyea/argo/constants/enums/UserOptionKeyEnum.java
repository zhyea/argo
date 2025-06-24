package com.zhyea.argo.constants.enums;

/**
 * 用户选项枚举
 *
 * @author robin
 * @since 2025/6/24 7:36
 */
public enum UserOptionKeyEnum {


	/**
	 * 最后访问的app
	 */
	LAST_VISIT_APP("lastVisitApp"),
	;


	public final String key;


	UserOptionKeyEnum(String key) {
		this.key = key;
	}
}
