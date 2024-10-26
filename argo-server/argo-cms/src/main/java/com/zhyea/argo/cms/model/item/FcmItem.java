package com.zhyea.argo.cms.model.item;

import com.zhyea.argo.constants.enums.FcScopeEnum;
import com.zhyea.argo.constants.enums.FcmTypeEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import lombok.Data;

import java.util.List;

/**
 * 组件模型信息
 *
 * @author robin
 */
@Data
public class FcmItem {


	/**
	 * 组件模型ID
	 */
	private Long fcmId;


	/**
	 * 作用域
	 * <p>
	 * see also {@link FcScopeEnum}
	 */
	private Integer scope;


	/**
	 * 应用ID
	 */
	private String appCode;


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
	private List<FcmPropItem> props;


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
