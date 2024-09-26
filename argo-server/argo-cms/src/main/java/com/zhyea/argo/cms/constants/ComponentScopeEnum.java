package com.zhyea.argo.cms.constants;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 组件作用域
 *
 * @author robin
 */
public enum ComponentScopeEnum implements CodeDescEnum {

	/**
	 * 全局
	 */
	GLOBAL(1, "全局"),


	/**
	 * 应用内
	 */
	APP(2, "应用内"),
	;


	public final int code;


	public final String desc;


	ComponentScopeEnum(int code, String desc) {
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
