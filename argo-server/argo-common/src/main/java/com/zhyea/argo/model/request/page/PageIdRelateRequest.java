package com.zhyea.argo.model.request.page;

import com.zhyea.argo.model.request.BaseOperateRequest;
import lombok.Data;

import jakarta.validation.constraints.NotNull;


/**
 * 应用页面ID相关请求
 *
 * @author robin
 */
@Data
public class PageIdRelateRequest extends BaseOperateRequest {


	/**
	 * 应用页面ID
	 */
	@NotNull(message = "应用页面ID不能为空")
	private Long pageId;

}
