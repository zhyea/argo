package com.zhyea.argo.cms.model.request.app;

import lombok.Data;

import jakarta.validation.constraints.NotNull;


/**
 * 应用更新请求
 *
 * @author robin
 */
@Data
public class AppEditRequest extends AppAddRequest {


	/**
	 * 应用id
	 */
	@NotNull(message = "应用id不能为空")
	private Long appId;

}
