package com.zhyea.argo.constants.enums;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 组件请求参数所属类型枚举
 *
 * @author robin
 * @since 2025/9/9 7:44
 */
public enum FciReqBindTypeEnum implements CodeDescEnum {


	/**
	 * 组件
	 */
	FCI(1, "组件"),


	/**
	 * 组件属性
	 */
	FCI_PROP(2, "组件属性"),

	;


	public final int code;

	public final String desc;

	FciReqBindTypeEnum(int code, String desc) {
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
