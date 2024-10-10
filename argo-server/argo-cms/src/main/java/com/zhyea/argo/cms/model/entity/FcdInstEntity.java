package com.zhyea.argo.cms.model.entity;

import com.zhyea.argo.model.entity.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 组件实例
 *
 * @author robin
 */
@Data
public class FcdInstEntity extends BaseEntity {


    /**
     * 页面ID
     */
    private Long pageId;


    /**
     * 组件ID
     */
    private Integer fcdId;


    /**
     * 数据链接
     */
    private String dataUrl;


    /**
     * 组件生效周期类型
     */
    private Integer effectivePeriodType;


    /**
     * 组件生效开始时间
     */
    private LocalDateTime effectiveStartTime;


    /**
     * 组件生效结束时间
     */
    private LocalDateTime effectiveEndTime;


    /**
     * 备注
     */
    private String remark;
}
