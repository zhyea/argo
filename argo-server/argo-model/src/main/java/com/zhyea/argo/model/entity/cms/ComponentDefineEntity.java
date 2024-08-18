package com.zhyea.argo.model.entity.cms;

import com.zhyea.argo.model.entity.BaseEntity;
import lombok.Data;

/**
 * 组件定义
 *
 * @author robin
 */
@Data
public class ComponentDefineEntity extends BaseEntity {


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
	 * 业务数据连接
	 */
	private String dataUrl;


	/**
	 * 业务数据解析规则
	 */
	private String dataParseRule;


	/**
	 * 组件备注
	 */
	private String remark;


}
