package com.zhyea.argo.cms.model.request.fci;

import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import com.zhyea.argo.constants.enums.HttpQueryMethodEnum;
import lombok.Data;
import org.chobit.commons.validation.EnumVal;

/**
 * 基础FCI请求
 *
 * @author robin
 * @since 2025/9/9 8:16
 */
@Data
public abstract class BaseFciRequest extends BaseOperateRequest {


	/**
	 * 数据相关URL
	 */
	private String dataUrl;


	/**
	 * 数据请求方式
	 */
	@EnumVal(enumClass = HttpQueryMethodEnum.class, message = "数据请求header错误")
	private Integer dataRequestMethod;


	/**
	 * 数据请求参数
	 */
	private String dataRequestParams;


	/**
	 * 数据请求头
	 */
	private String dataRequestHeaders;
}
