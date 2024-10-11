package com.zhyea.argo.cms.model.request.fcm;

import com.zhyea.argo.cms.constants.FcScopeEnum;
import com.zhyea.argo.cms.constants.FcmTypeEnum;
import com.zhyea.argo.cms.constants.YesOrNo;
import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import lombok.Data;
import org.chobit.commons.validation.EnumVal;
import org.chobit.commons.validation.NoEmptyJson;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 前端组件模型新增请求
 *
 * @author robin
 */
@Data
public class FcmAddRequest extends BaseOperateRequest {


	/**
	 * 组件名称
	 */
	@NotBlank(message = "组件名称不能为空")
	private String name;


	/**
	 * 图标
	 */
	private String icon;


	/**
	 * 类型
	 */
	@EnumVal(enumClass = FcmTypeEnum.class, message = "组件类型错误")
	@NotNull(message = "组件类型不能为空")
	private Integer type;


	/**
	 * 作用域
	 */
	@EnumVal(enumClass = FcScopeEnum.class, message = "组件作用域错误")
	@NotNull(message = "组件作用域不能为空")
	private Integer scope;


	/**
	 * 属性
	 */
	@NoEmptyJson(message = "组件属性不能为空")
	@NotBlank(message = "组件属性不能为空")
	private String props;


	/**
	 * 是否需要绑定数据标记
	 */
	@EnumVal(enumClass = YesOrNo.class, message = "是否需要绑定数据标记错误")
	@NotNull(message = "是否需要绑定数据标记不能为空")
	private Integer dataBindFlag;


	/**
	 * 组件备注
	 */
	private String remark;


}
