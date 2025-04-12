package com.zhyea.argo.cms.model.request.fci;

import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import lombok.Data;

import jakarta.validation.constraints.NotNull;


/**
 * 组件属性关闭请求
 *
 * @author robin
 */
@Data
public class FciPropCloseRequest extends BaseOperateRequest {


	/**
	 * 组件实例id
	 */
	@NotNull(message = "fciId不能为空")
	private Long fciId;


	/**
	 * 属性key
	 */
	@NotNull(message = "属性Key不能为空")
	private String propKey;
}
