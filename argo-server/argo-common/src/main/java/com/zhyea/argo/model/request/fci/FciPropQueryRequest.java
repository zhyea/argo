package com.zhyea.argo.model.request.fci;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chobit.commons.model.request.BasePageRequest;


/**
 * 组件实例查询请求
 *
 * @author robin
 */
@Data
public class FciPropQueryRequest extends BasePageRequest {


	/**
	 * 组件实例ID
	 */
	@NotNull(message = "组件实例ID不能为空")
	private Long fciId;


	/**
	 * 关键字
	 */
	private String keyword;


}
