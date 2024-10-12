package com.zhyea.argo.cms.constants.enums;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 是或否枚举
 *
 * @author robin
 */
public enum YesOrNo implements CodeDescEnum {


	/**
	 * 是
	 */
	YES(1, "是"),


	/**
	 * 否
	 */
	NO(0, "否"),
	;


	public final int code;


	public final String desc;


	YesOrNo(int code, String desc) {
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
