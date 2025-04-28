package com.zhyea.argo.cms.model.request;

import lombok.Data;

/**
 * 基础操作请求
 *
 * @author robin
 */
@Data
public abstract class BaseOperateRequest {


	/**
	 * 操作人编码
	 */
	private String operatorCode;

}
