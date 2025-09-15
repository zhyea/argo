package com.zhyea.argo.data.entity.cms;

import com.zhyea.argo.constants.enums.*;
import com.zhyea.argo.data.entity.BaseEntity;
import lombok.Data;

/**
 * 组件定义
 *
 * @author robin
 */
@Data
public class FcmEntity extends BaseEntity {


	/**
	 * 作用域
	 * <p>
	 * see also {@link FcScopeEnum}
	 */
	private Integer scope;


	/**
	 * 应用ID
	 */
	private Long appId;


	/**
	 * 组件名称
	 */
	private String name;


	/**
	 * 图标
	 */
	private String icon;


	/**
	 * 类型
	 * <p>
	 * see also {@link FcmTypeEnum}
	 */
	private Integer type;


	/**
	 * 属性
	 */
	private String props;


	/**
	 * 组件备注
	 */
	private String remark;


	/**
	 * 组件唯一编码
	 */
	private String uniqCode;

}
