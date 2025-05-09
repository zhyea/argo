package com.zhyea.argo.constants.enums;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 生效周期类型枚举
 *
 * @author robin
 */
public enum EffectivePeriodTypeEnum implements CodeDescEnum {


	/**
	 * 长期有效
	 */
	LONG_TERM(1, "长期有效"),


	/**
	 * 固定有效期
	 */
	FIXED_TERM(2, "固定有效期"),
	;


	public final int code;


	public final String desc;


	EffectivePeriodTypeEnum(int code, String desc) {
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
