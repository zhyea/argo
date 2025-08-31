package com.zhyea.argo.constants.enums;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 组件实例使用范围枚举
 *
 * @author robin
 * @since 2025/8/31 21:04
 */
public enum FciUsageScopeEnum implements CodeDescEnum {

	/**
	 * 全局
	 */
	GLOBAL(1, "全局"),

	/**
	 * 应用内
	 */
	APP(2, "应用内"),

	/**
	 * 指定页面
	 */
	PAGE(3, "指定页面"),

	;


	public final int code;

	public final String desc;

	FciUsageScopeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}


	@Override
	public String getDesc() {
		return this.desc;
	}


	@Override
	public int getCode() {
		return this.code;
	}
}
