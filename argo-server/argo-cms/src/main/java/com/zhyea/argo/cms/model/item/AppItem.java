package com.zhyea.argo.cms.model.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

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

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;


	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

}
