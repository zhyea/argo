package com.zhyea.argo.cms.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 新增应用请求
 *
 * @author robin
 */
@Data
public class AppAddRequest extends BaseOperateRequest {



	@NotBlank(message = "应用名称不能为空")
	private String appName;


	private String icon;


	private String remark;


}
