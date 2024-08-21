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
	 * 属性生效周期类型
	 */
	private Integer effectPeriodType;


	/**
	 * 备注
	 */
	private String remark;


}
