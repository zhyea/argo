package com.zhyea.argo.cms.model.request.app;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 应用页面查询请求
 *
 * @author robin
 */
@Data
public class AppPageQueryRequest {


	/**
	 * 应用id
	 */
	@NotNull(message = "应用id不能为空")
	private Long appId;


	/**
	 * 关键字
	 */
	private String keyword;


}
