package com.zhyea.argo.model.request.page;

import lombok.Data;

import jakarta.validation.constraints.NotNull;


/**
 * 应用页面修改请求
 *
 * @author robin
 */
@Data
public class PageEditRequest extends PageAddRequest {


	/**
	 * 页面id
	 */
	@NotNull(message = "pageId不能为空")
	private Long pageId;


}
