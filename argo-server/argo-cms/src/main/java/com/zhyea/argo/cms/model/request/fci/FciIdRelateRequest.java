package com.zhyea.argo.cms.model.request.fci;

import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 组件实例id相关请求
 *
 * @author robin
 */
@Data
public class FciIdRelateRequest extends BaseOperateRequest {


	/**
	 * 组件实例id
	 */
	@NotNull(message = "fciId不能为空")
	private Long fciId;

}
