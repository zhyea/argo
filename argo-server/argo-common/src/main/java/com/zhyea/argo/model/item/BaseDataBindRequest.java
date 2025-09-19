package com.zhyea.argo.model.item;

import lombok.Data;

/**
 * 数据绑定请求抽象类
 *
 * @author robin
 * @since 2025/9/19 20:26
 */
@Data
public abstract class BaseDataBindRequest {


	/**
	 * 组件实例(属性)ID
	 */
	private Long id;

	/**
	 * 数据链接
	 */
	private String dataUrl;


	/**
	 * 数据请求方式
	 */
	private Integer dataRequestMethod;


	/**
	 * 数据请求参数
	 */
	private String dataRequestParams;


	/**
	 * 数据请求头
	 */
	private String dataRequestHeaders;


	/**
	 * 属性值选择器
	 */
	private String propValueSelector;
}
