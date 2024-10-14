package com.zhyea.argo.model.entity;

import com.zhyea.argo.model.BaseEntity;
import lombok.Data;

/**
 * 应用页面信息
 *
 * @author robin
 */
@Data
public class AppPageEntity extends BaseEntity {


	/**
	 * 应用ID
	 */
	private Long appId;


	/**
	 * 页面代码（全局唯一）
	 */
	private String pageCode;


	/**
	 * 页面名称
	 */
	private String pageName;


	/**
	 * 描述信息
	 */
	private String remark;
}
