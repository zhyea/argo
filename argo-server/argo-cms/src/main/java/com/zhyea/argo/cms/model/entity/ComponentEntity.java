package com.zhyea.argo.cms.model.entity;

import com.zhyea.argo.model.entity.BaseEntity;
import lombok.Data;

/**
 * 组件定义
 *
 * @author robin
 */
@Data
public class ComponentEntity extends BaseEntity {


	/**
	 * 组件名称
	 */
	private String name;


	/**
	 * 图标
	 */
	private String icon;


	/**
	 * 类型
	 */
	private Integer type;


	/**
	 * 属性
	 */
	private String props;


	/**
	 * 数据来源模式
	 */
	private Integer dataSourceMode;


	/**
	 * 组件备注
	 */
	private String remark;


}
