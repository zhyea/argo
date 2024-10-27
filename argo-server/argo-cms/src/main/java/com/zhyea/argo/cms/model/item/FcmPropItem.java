package com.zhyea.argo.cms.model.item;

import com.zhyea.argo.constants.enums.FcmPropTypeEnum;
import lombok.Data;
import org.chobit.commons.validation.EnumVal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 组件属性
 *
 * @author robin
 */
@Data
public class FcmPropItem {


	/**
	 * 属性
	 */
	@NotBlank(message = "属性key不可为空")
	private String propKey;


	/**
	 * 属性类型
	 */
	@EnumVal(enumClass = FcmPropTypeEnum.class, message = "属性类型值不正确")
	@NotNull(message = "属性类型不可为空")
	private Integer propType;


	/**
	 * 是否必填
	 */
	@NotNull(message = "属性是否必填不可为空")
	private Integer required;


	/**
	 * 属性描述
	 */
	private String propDesc;


}
