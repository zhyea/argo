package com.zhyea.argo.data.entity.cms;

import com.zhyea.argo.data.entity.BaseEntity;
import lombok.Data;

/**
 * 应用页面信息
 *
 * @author robin
 */
@Data
public class PageEntity extends BaseEntity {


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
