package com.zhyea.argo.cms.model.item;

import com.zhyea.argo.constants.enums.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 组件实例信息
 *
 * @author robin
 */
@Data
public class FciItem {


	/**
	 * 组件模型ID
	 */
	private Long id;


	/**
	 * 组件名称
	 */
	private String name;


	/**
	 * 类型
	 * <p>
	 * see also {@link FcmTypeEnum}
	 */
	private Integer type;


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
	 * 应用名称
	 */
	private String appName;


	/**
	 * 图标
	 */
	private String icon;


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


	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;


	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

}
