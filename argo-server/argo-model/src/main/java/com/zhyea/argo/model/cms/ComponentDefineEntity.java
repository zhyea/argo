package com.zhyea.argo.model.cms;

import com.zhyea.argo.model.BaseEntity;
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
	 * 组件备注
	 */
	private String remark;


}
