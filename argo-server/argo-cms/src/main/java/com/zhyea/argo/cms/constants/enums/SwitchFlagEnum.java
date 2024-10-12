package com.zhyea.argo.cms.constants.enums;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 开关标记枚举
 *
 * @author robin
 */
public enum SwitchFlagEnum implements CodeDescEnum {


	/**
	 * 开启
	 */
	open(1, "开启"),


	/**
	 * 关闭
	 */
	closed(2, "关闭"),

	;


	public final int code;


	public final String desc;


	SwitchFlagEnum(int code, String desc) {
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
