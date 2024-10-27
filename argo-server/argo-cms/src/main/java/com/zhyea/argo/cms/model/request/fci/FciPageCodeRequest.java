package com.zhyea.argo.cms.model.request.fci;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 通过页面编码查询组件实例请求
 *
 * @author robin
 */
@Data
public class FciPageCodeRequest {


	@NotBlank(message = "页面编码不能为空")
	private String pageCode;

}
