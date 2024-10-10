package com.zhyea.argo.cms.model.item;

import lombok.Data;

/**
 * 组件属性
 *
 * @author robin
 */
@Data
public class FcmPropItem {


	/**
	 * 属性
	 */
	private String propKey;


	/**
	 * 属性描述
	 */
	private String propDesc;


	/**
	 * 属性类型
	 */
	private Integer propType;


	/**
	 * 是否必填
	 */
	private Boolean required;


}
