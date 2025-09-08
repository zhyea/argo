package com.zhyea.argo.constants.enums;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 数据绑定标记枚举
 *
 * @author robin
 * @since 2025/9/8 22:57
 */
public enum DataBindFlagEnum implements CodeDescEnum {


	/**
	 * 不绑定
	 */
	NOT_BIND(0, "不绑定"),

	/**
	 * 绑定数据
	 */
	BIND_DATA(1, "绑定"),


	/**
	 * 继承
	 */
	INHERIT(2, "继承"),
	;


	public final int code;
	public final String desc;

	DataBindFlagEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}


	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public String getDesc() {
		return this.desc;
	}
}
