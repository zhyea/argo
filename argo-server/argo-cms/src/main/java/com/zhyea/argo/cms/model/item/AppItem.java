package com.zhyea.argo.cms.model.item;

import lombok.Data;

/**
 * 应用信息
 *
 * @author robin
 */
@Data
public class AppItem {


	/**
	 * ID
	 */
	private Long id;


	/**
	 * 应用Code
	 */
	private String appCode;


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
