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


	private Integer componentId;


	private String prop;


	private String propValue;


	private LocalDateTime startTime;


	private LocalDateTime endTime;

}
