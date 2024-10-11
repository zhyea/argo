package com.zhyea.argo.cms.model.request.app;

import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

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
