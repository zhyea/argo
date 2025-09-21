package com.zhyea.argo.model.request.fci;

import lombok.Data;

/**
 * 属性key相关请求
 *
 * @author robin
 * @since 2025/9/21 15:17
 */
@Data
public class FciPropKeyRequest {


	/**
	 * 组件实例ID
	 */
	private Long fciId;

	/**
	 * 组件属性key
	 */
	private String propKey;

}
