package com.zhyea.argo.cms.model.request.fci;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import lombok.Data;
import org.chobit.commons.contract.Checkable;
import org.chobit.commons.exception.ParamException;
import org.chobit.commons.validation.EnumVal;
import org.chobit.commons.validation.WholeCheck;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

import static org.chobit.commons.utils.StrKit.isBlank;


/**
 * 组件实例属性新增请求
 *
 * @author robin
 */
@WholeCheck(message = "组件实例属性信息有误")
@Data
public class FciPropAddRequest extends BaseOperateRequest implements Checkable {


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
	private String propValue;


	/**
	 * 数据绑定标识
	 */
	@EnumVal(enumClass = YesOrNo.class, message = "数据绑定标识错误")
	@NotNull(message = "数据绑定标识不能为空")
	private Integer dataBindFlag;


	/**
	 * 数据相关URL
	 */
	private String dataUrl;


	/**
	 * 数据值选择器
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime effectiveStartTime;


	/**
	 * 属性生效结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime effectiveEndTime;


	/**
	 * 描述
	 */
	private String remark;


	@Override
	public boolean check() throws ParamException {
		if (EffectivePeriodTypeEnum.FIXED_TERM.is(effectivePeriodType)) {
			if (null == effectiveStartTime || null == effectiveEndTime) {
				return false;
			}

			// 新增时，开始时间不能<=当前时间
			if (effectiveStartTime.isBefore(LocalDateTime.now())
					|| effectiveStartTime.isEqual(LocalDateTime.now())) {
				return false;
			}

			// 结束时间需要大于开始时间
			if (!effectiveEndTime.isAfter(effectiveStartTime)) {
				return false;
			}
		}

		if (YesOrNo.YES.is(getDataBindFlag()) && (isBlank(getPropValueSelector()) || isBlank(getDataUrl()))) {
			return false;
		}

		if (YesOrNo.NO.is(getDataBindFlag()) && isBlank(getPropValue())) {
			return false;
		}
		return true;
	}
}
