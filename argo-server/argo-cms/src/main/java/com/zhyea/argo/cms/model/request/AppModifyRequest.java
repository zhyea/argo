package com.zhyea.argo.cms.model.request;

import lombok.Data;

/**
 * 应用更新请求
 *
 * @author robin
 */
@Data
public class AppModifyRequest extends AppAddRequest {


	/**
	 * 应用id
	 */
	private Integer appId;

}
