package com.zhyea.argo.cms.model.entity;

import com.zhyea.argo.model.entity.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 组件属性
 *
 * @author robin
 */
@Data
public class ComponentPropEntity extends BaseEntity {


	/**
	 * 组件ID
	 */
	private Integer componentId;


	/**
	 * 属性key
	 */
	private String propKey;


	/**
	 * 属性值
	 */
	private String propValue;


	/**
	 * 属性生效周期类型
	 */
	private Integer effectPeriodType;


	/**
	 * 属性生效开始时间
	 */
	private LocalDateTime effectStartTime;


	/**
	 * 属性生效结束时间
	 */
	private LocalDateTime effectEndTime;

}
