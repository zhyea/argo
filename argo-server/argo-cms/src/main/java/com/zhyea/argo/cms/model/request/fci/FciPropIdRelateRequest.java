package com.zhyea.argo.cms.model.request.fci;

import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import lombok.Data;

import jakarta.validation.constraints.NotNull;


/**
 * 属性ID相关请求
 *
 * @author robin
 */
@Data
public class FciPropIdRelateRequest extends BaseOperateRequest {


	/**
	 * 属性ID
	 */
	@NotNull(message = "属性Id不能为空")
	private Long propId;


}
