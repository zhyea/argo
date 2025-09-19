package com.zhyea.argo.model.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 应用页面信息
 *
 * @author robin
 */
@Data
public class PageItem {


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
