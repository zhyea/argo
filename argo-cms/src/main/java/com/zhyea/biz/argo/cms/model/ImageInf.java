package com.zhyea.biz.argo.cms.model;

import lombok.Data;

import java.io.Serializable;


/**
 * 图片信息
 *
 * @author robin
 */
@Data
public class ImageInf implements Serializable {


    /**
     * 图片ID
     */
    private Long id;

    /**
     * 图片URL
     */
    private String url;

}
