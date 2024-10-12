package com.zhyea.argo.cms.model.request.fci;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 组件实例修改请求
 * @author robin
 */
@Data
public class FciModifyRequest extends FciAddRequest{


	/**
	 * 组件实例id
	 */
	@NotNull(message = "fciId不能为空")
	private Long fciId;

}
