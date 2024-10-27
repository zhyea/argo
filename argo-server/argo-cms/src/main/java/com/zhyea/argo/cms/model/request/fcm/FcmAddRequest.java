package com.zhyea.argo.cms.model.request.fcm;

import com.zhyea.argo.cms.model.item.FcmPropItem;
import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import com.zhyea.argo.constants.enums.FcScopeEnum;
import com.zhyea.argo.constants.enums.FcmTypeEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import lombok.Data;
import org.chobit.commons.validation.EnumVal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

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
	 * 应用ID
	 */
	private String appCode;


	/**
	 * 属性
	 */
	@Valid
	@NotEmpty(message = "组件属性不能为空")
	private List<FcmPropItem> props;


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
