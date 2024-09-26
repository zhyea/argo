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
	 * 作用域
	 */
	private Integer scope;


	/**
	 * 属性
	 */
	private String props;


	/**
	 * 是否绑定数据
	 */
	private Integer bindData;


	/**
	 * 组件备注
	 */
	private String remark;


}
