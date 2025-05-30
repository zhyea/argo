package com.zhyea.argo.cms.model.request.page;

import lombok.Data;

import jakarta.validation.constraints.NotNull;


/**
 * 应用页面查询请求
 *
 * @author robin
 */
@Data
public class PageListQueryRequest {


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
