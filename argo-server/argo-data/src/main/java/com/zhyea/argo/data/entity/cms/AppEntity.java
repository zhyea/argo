package com.zhyea.argo.data.entity.cms;

import com.zhyea.argo.data.entity.BaseEntity;
import lombok.Data;

/**
 * 应用实体
 *
 * @author robin
 */
@Data
public class AppEntity extends BaseEntity {


	/**
	 * 应用Code
	 */
	private String appCode;


	/**
	 * 应用名称
	 */
	private String appName;


	/**
	 * 应用类型
	 */
	private Integer appType;


	/**
	 * 应用icon地址
	 */
	private String icon;


	/**
	 * 应用描述
	 */
	private String remark;


}
