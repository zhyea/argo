package com.zhyea.argo.cms.constants;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 组件作用域
 *
 * @author robin
 */
public enum FcScopeEnum implements CodeDescEnum {


	/**
	 * 全局组件
	 */
	GLOBAL(1, "全局"),


	/**
	 * 应用组件
	 */
	APP(2, "应用"),
	;


	public final int code;


	public final String desc;


	FcScopeEnum(int code, String desc) {
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
