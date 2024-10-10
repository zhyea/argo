package com.zhyea.argo.cms.constants;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 基础组件类型
 *
 * @author robin
 */
public enum FcmTypeEnum implements CodeDescEnum {



	/**
	 * 字符串
	 */
	STRING(1, "字符串"),


	/**
	 * 图片
	 */
	IMAGE(2, "图片"),


	/**
	 * 组合类型
	 */
	COMBINE(3, "组合类型"),
	;


	public final int code;


	public final String desc;


	FcmTypeEnum(int code, String desc) {
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
