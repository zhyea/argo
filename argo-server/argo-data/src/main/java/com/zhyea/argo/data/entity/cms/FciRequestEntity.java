package com.zhyea.argo.data.entity.cms;

import com.zhyea.argo.data.entity.BaseEntity;
import lombok.Data;

/**
 * 组件相关请求信息
 *
 * @author robin
 * @since 2025/9/8 23:02
 */
@Data
public class FciRequestEntity extends BaseEntity {


	/**
	 * 数据绑定类型
	 */
	private Integer bindType;


	/**
	 * 组件实例ID或组件实例属性ID
	 */
	private Long belongId;


	/**
	 * 数据绑定URL
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


}
