package com.zhyea.argo.model.request.page;

import com.zhyea.argo.model.request.BaseOperateRequest;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 应用页面新增请求
 *
 * @author robin
 */
@Data
public class PageAddRequest extends BaseOperateRequest {


	@NotNull(message = "应用ID不能为空")
	private Long appId;


	@NotBlank(message = "页面代码不能为空")
	private String pageCode;


	@NotBlank(message = "页面名称不能为空")
	private String pageName;


	private String remark;

}
