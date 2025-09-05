package com.zhyea.argo.constants.enums;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * http查询method枚举
 *
 * @author robin
 * @since 2025/9/4 22:33
 */
public enum HttpQueryMethodEnum implements CodeDescEnum {


	/**
	 * get
	 */
	GET(1, "GET"),


	/**
	 * post
	 */
	POST(2, "POST"),
	;


	public final int code;

	public final String desc;

	HttpQueryMethodEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}


	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public int getCode() {
		return code;
	}


	public static HttpQueryMethodEnum codeOf(int code) {
		for (HttpQueryMethodEnum value : values()) {
			if (value.code == code) {
				return value;
			}
		}
		return null;
	}
}
