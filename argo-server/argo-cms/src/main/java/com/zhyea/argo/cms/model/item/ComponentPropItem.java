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
	private String prop;


	/**
	 * 名称
	 */
	private String name;


	/**
	 * 备注
	 */
	private String remark;


	/**
	 * 是否是可变的
	 */
	private Integer changeable;

}
