package com.zhyea.argo.model;

import lombok.Data;

import java.util.Date;

/**
 * base entity
 *
 * @author robin
 */
@Data
public abstract class BaseEntity {


    /**
     * 记录ID
     */
    private Long id;


    /**
     * 操作人code
     */
    private String operatorCode;


    /**
     * 删除标记
     */
    private Integer deleted;


    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 更新时间
     */
    private Date updateTime;


}
