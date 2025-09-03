package com.zhyea.argo.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 页面详情请求
 *
 * @author robin
 * @since 2025/9/3 23:18
 */
@Data
public class PageDetailRequest {


	@NotBlank(message = "页面编码不能为空")
	private String pageCode;


}
