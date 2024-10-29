package com.zhyea.argo.cms.model.request.fcm;

import lombok.Data;
import org.chobit.commons.model.request.BasePageRequest;

/**
 * 查询组件模型查询请求
 *
 * @author robin
 */
@Data
public class FcmQueryRequest extends BasePageRequest {


	/**
	 * 应用id
	 */
	private String appCode;


	/**
	 * 关键字
	 */
	private String keyword;


}
