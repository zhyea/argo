package com.zhyea.argo.cms.model.request.fci;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * FCI属性修改请求
 *
 * @author robin
 */
@Data
public class FciPropEditRequest extends FciPropAddRequest {


	/**
	 * 属性Id
	 */
	@NotNull(message = "属性Id不能为空")
	private Long propId;

}
