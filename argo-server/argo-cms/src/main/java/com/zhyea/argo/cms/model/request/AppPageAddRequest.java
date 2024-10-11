package com.zhyea.argo.cms.model.request;

import lombok.Data;

/**
 * 应用页面新增请求
 *
 * @author robin
 */
@Data
public class AppPageAddRequest extends BaseOperateRequest {



	private String appId;


	private String pageCode;


	private String pageName;


	private String remark;

}
