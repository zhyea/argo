package com.zhyea.argo.cms.model.entity;

import com.zhyea.argo.model.entity.BaseEntity;
import lombok.Data;

/**
 * 应用实体
 *
 * @author robin
 */
@Data
public class AppEntity extends BaseEntity {


    /**
     * 应用名称
     */
    private String appName;


    /**
     * 应用icon地址
     */
    private String icon;


    /**
     * 应用描述
     */
    private String remark;


}
