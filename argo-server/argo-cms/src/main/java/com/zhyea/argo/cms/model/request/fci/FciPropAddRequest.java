package com.zhyea.argo.cms.model.request.fci;

import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import lombok.Data;
import org.chobit.commons.validation.EnumVal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


/**
 * 组件实例属性新增请求
 *
 * @author robin
 */
@Data
public class FciPropAddRequest extends BaseOperateRequest {


	/**
	 * 组件实例ID
	 */
	@NotNull(message = "fciId不能为空")
	private Long fciId;


	/**
	 * 属性key
	 */
	@NotBlank(message = "属性Key不能为空")
	private String propKey;


	/**
	 * 属性值
	 */
	@NotBlank(message = "属性值不能为空")
	private String propValue;


	/**
	 * 属性值选择器
	 */
	private String propValueSelector;


	/**
	 * 属性生效周期类型
	 */
	@EnumVal(enumClass = EffectivePeriodTypeEnum.class, message = "属性生效周期类型错误")
	@NotNull(message = "属性生效周期类型不能为空")
	private Integer effectivePeriodType;


	/**
	 * 属性生效开始时间
	 */
	private LocalDateTime effectiveStartTime;


	/**
	 * 属性生效结束时间
	 */
	private LocalDateTime effectiveEndTime;


	/**
	 * 描述
	 */
	private String remark;

}
