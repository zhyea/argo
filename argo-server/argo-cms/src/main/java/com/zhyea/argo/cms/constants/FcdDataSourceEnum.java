package com.zhyea.argo.cms.constants;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 组件数据来源
 *
 * @author robin
 */
public enum FcdDataSourceEnum implements CodeDescEnum {


	/**
	 * 常量
	 */
	DIRECT(1, "常量"),


	/**
	 * 远程获取
	 */
	REMOTE(2, "远程获取"),


	/**
	 * 组合模式
	 */
	COMBO(3, "组合模式"),
	;


	public final int code;


	public final String desc;


	FcdDataSourceEnum(int code, String desc) {
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
