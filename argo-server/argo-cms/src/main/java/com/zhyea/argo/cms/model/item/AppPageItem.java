package com.zhyea.argo.cms.model.item;

import lombok.Data;

/**
 * 应用页面信息
 *
 * @author robin
 */
@Data
public class AppPageItem {


	/**
	 * ID
	 */
	private Long pageId;


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
