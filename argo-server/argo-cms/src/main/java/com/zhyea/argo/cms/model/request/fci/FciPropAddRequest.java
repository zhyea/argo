package com.zhyea.argo.cms.model.request.fci;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhyea.argo.constants.enums.DataBindFlagEnum;
import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.tools.Args;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chobit.commons.contract.Checkable;
import org.chobit.commons.exception.ParamException;
import org.chobit.commons.validation.EnumVal;
import org.chobit.commons.validation.WholeCheck;

import java.time.LocalDateTime;

import static com.zhyea.argo.constants.ResponseCode.*;


/**
 * 组件实例属性新增请求
 *
 * @author robin
 */
@WholeCheck(message = "组件实例属性信息有误")
@Data
public class FciPropAddRequest extends BaseFciRequest implements Checkable {


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
	@EnumVal(enumClass = DataBindFlagEnum.class, message = "数据绑定标识错误")
	@NotNull(message = "数据绑定标识不能为空")
	private Integer dataBindFlag;


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
			Args.check(effectiveStartTime != null && effectiveEndTime != null, FCI_PROP_EFFECTIVE_TIME_IS_EMPTY);

			// 新增时，开始时间不能<=当前时间
			Args.check(!(effectiveStartTime.isBefore(LocalDateTime.now()) || effectiveStartTime.isEqual(LocalDateTime.now())),
					FCI_PROP_EFFECTIVE_START_TIME_AFTER_NOW);
			// 结束时间需要大于开始时间
			Args.check(effectiveEndTime.isAfter(effectiveStartTime), FCI_PROP_EFFECTIVE_END_TIME_AFTER_START);
		}

		if (DataBindFlagEnum.BIND_DATA.is(getDataBindFlag())) {
			Args.checkNotBlank(this.getDataUrl(), DATA_BIND_URL_IS_BLANK);
			Args.checkNotNull(this.getDataRequestMethod(), PROP_DATA_REQUEST_METHOD_IS_NULL);

			//Args.checkNotBlank(dataRequestParams, PROP_DATA_REQUEST_PARAMS_IS_BLANK);
			//Args.checkNotBlank(this.getDataRequestHeaders(), PROP_DATA_REQUEST_HEADERS_IS_BLANK);
			Args.checkNotBlank(propValueSelector, PROP_DATA_VALUE_SELECTOR_IS_BLANK);
		}

		return true;
	}
}
