package com.zhyea.argo.constants.enums;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 应用类型枚举
 *
 * @author robin
 * @since 2025/7/3 7:01
 */
public enum AppTypeEnum implements CodeDescEnum {

	/**
	 * WEB
	 */
	WEB(1, "WEB"),


	/**
	 * APP
	 */
	APP(2, "APP"),

	;


	public final int code;

	public final String desc;

	AppTypeEnum(int code, String desc) {
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
