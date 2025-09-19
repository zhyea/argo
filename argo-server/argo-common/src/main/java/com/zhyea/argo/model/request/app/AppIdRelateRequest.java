package com.zhyea.argo.model.request.app;

import com.zhyea.argo.model.request.BaseOperateRequest;
import lombok.Data;

import jakarta.validation.constraints.NotNull;


/**
 * 应用ID相关请求
 *
 * @author robin
 */
@Data
public class AppIdRelateRequest extends BaseOperateRequest {


	/**
	 * 应用ID
	 */
	@NotNull(message = "应用ID不能为空")
	private Long appId;


}
