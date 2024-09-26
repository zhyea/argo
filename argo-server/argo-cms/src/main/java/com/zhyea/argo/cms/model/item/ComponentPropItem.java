package com.zhyea.argo.cms.model.item;

import lombok.Data;

/**
 * 组件属性
 *
 * @author robin
 */
@Data
public class ComponentPropItem {


	/**
	 * 属性
	 */
	private String propKey;


	/**
	 * 属性描述
	 */
	private String propDesc;


	/**
	 * 是否必填
	 */
	private Boolean required;


}
