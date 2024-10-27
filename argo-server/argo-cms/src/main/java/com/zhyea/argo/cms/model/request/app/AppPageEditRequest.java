package com.zhyea.argo.cms.model.request.app;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 应用页面修改请求
 *
 * @author robin
 */
@Data
public class AppPageEditRequest extends AppPageAddRequest {


	/**
	 * 页面id
	 */
	@NotNull(message = "pageId不能为空")
	private Long pageId;


}
