package com.zhyea.argo.cms.model.entity;

import com.zhyea.argo.cms.constants.enums.*;
import com.zhyea.argo.model.entity.BaseEntity;
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
	private String appId;


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
	 * 是否需要绑定数据标记
	 * <p>
	 * see also {@link YesOrNo}
	 */
	private Integer dataBindFlag;


	/**
	 * 组件备注
	 */
	private String remark;


}
