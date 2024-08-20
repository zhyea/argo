package com.zhyea.argo.cms.model.entity;

import com.zhyea.argo.model.entity.BaseEntity;
import lombok.Data;

/**
 * 组件实例
 *
 * @author robin
 */
@Data
public class ComponentInstanceEntity extends BaseEntity {


	/**
	 * 组件ID
	 */
	private Integer componentId;


	/**
	 * 数据链接
	 */
	private String dataUrl;
}
