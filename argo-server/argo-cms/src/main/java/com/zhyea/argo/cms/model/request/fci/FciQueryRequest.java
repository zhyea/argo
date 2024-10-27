package com.zhyea.argo.cms.model.request.fci;

import lombok.Data;
import org.chobit.commons.model.request.BasePageRequest;

import javax.validation.constraints.NotNull;


/**
 * 组件实例查询请求
 *
 * @author robin
 */
@Data
public class FciQueryRequest extends BasePageRequest {


	/**
	 * 应用ID
	 */
	@NotNull(message = "应用ID不能为空")
	private Long appId;


	/**
	 * 关键字
	 */
	private String keyword;


}
