package com.zhyea.argo.cms.model.request.app;

import lombok.Data;
import org.chobit.commons.model.request.BasePageRequest;

/**
 * 应用列表查询请求
 *
 * @author robin
 * @since 2025/7/1 8:26
 */
@Data
public class AppPageRequest extends BasePageRequest {


	/**
	 * 关键字
	 */
	private String keyword;

}
