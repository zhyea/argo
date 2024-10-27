package com.zhyea.argo.constants.enums;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 组件属性类型
 *
 * @author robin
 */
public enum FcmPropTypeEnum implements CodeDescEnum {


	/**
	 * 简单属性
	 */
	SIMPLE(1, "简单属性"),


	/**
	 * 级联属性
	 */
	CASCADE(2, "级联属性"),
	;


	public final int code;


	public final String desc;


	FcmPropTypeEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}


	@Override
	public int getCode() {
		return code;
	}


	@Override
	public String getDesc() {
		return desc;
	}
}
