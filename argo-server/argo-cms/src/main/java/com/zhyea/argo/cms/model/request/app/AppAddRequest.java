package com.zhyea.argo.cms.model.request.app;

import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 新增应用请求
 *
 * @author robin
 */
@Data
public class AppAddRequest extends BaseOperateRequest {


	/**
	 * 应用ID
	 */
	@NotBlank(message = "应用ID不能为空")
	private String appCode;


	/**
	 * 应用名称
	 */
	@NotBlank(message = "应用名称不能为空")
	private String appName;


	/**
	 * 应用图标
	 */
	private String icon;


	/**
	 * 应用描述
	 */
	private String remark;


}
