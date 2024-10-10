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
public class FciPropEntity extends BaseEntity {


    /**
     * 组件实例ID
     */
    private Long fciId;


    /**
     * 属性key
     */
    private String propKey;


    /**
     * 属性值
     */
    private String propValue;


    /**
     * 属性值选择器
     */
    private String propValueSelector;



    /**
     * 属性生效周期类型
     */
    private Integer effectivePeriodType;


    /**
     * 属性生效开始时间
     */
    private LocalDateTime effectiveStartTime;


    /**
     * 属性生效结束时间
     */
    private LocalDateTime effectiveEndTime;


    /**
     * 描述
     */
    private String remark;

}
