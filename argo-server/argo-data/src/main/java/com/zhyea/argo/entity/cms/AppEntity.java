package com.zhyea.argo.entity.cms;

import com.zhyea.argo.entity.BaseEntity;
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
	 * 应用icon地址
	 */
	private String icon;


	/**
	 * 应用描述
	 */
	private String remark;


}
