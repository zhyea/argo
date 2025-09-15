package com.zhyea.argo.cms.model.request.fci;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhyea.argo.constants.enums.DataBindFlagEnum;
import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.constants.enums.FciUsageScopeEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import com.zhyea.argo.tools.Args;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chobit.commons.contract.Checkable;
import org.chobit.commons.exception.ParamException;
import org.chobit.commons.utils.Collections2;
import org.chobit.commons.validation.EnumVal;
import org.chobit.commons.validation.WholeCheck;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;

import static com.zhyea.argo.constants.ResponseCode.*;

/**
 * 组件实例新增请求
 *
 * @author robin
 */
@WholeCheck(message = "组件实例信息有误")
@Data
public class FciAddRequest extends BaseFciRequest implements Checkable {

	/**
	 * 应用ID
	 */
	@NotNull(message = "应用Id不能为空")
	private Long appId;


	/**
	 * 组件模型ID
	 */
	@NotNull(message = "fcmId不能为空")
	private Long fcmId;


	/**
	 * 组件名称
	 */
	@NotBlank(message = "name不能为空")
	private String name;


	/**
	 * 组件开关标志
	 */
	@EnumVal(enumClass = FciUsageScopeEnum.class, message = "使用范围取值错误")
	@NotNull(message = "使用范围不能为空")
	private Integer usageScope;


	/**
	 * 组件开关标志
	 */
	@EnumVal(enumClass = YesOrNo.class, message = "开关标志错误")
	private Integer switchFlag;


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
	 * 组件生效周期类型
	 */
	@EnumVal(enumClass = EffectivePeriodTypeEnum.class, message = "生效周期类型错误")
	@NotNull(message = "生效周期类型不能为空")
	private Integer effectivePeriodType;


	/**
	 * 组件生效时间范围
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	List<LocalDateTime> effectiveTimeRange;


	/**
	 * 备注
	 */
	private String remark;


	@Override
	public boolean check() throws ParamException {
		if (EffectivePeriodTypeEnum.FIXED_TERM.is(effectivePeriodType)) {
			Args.check(Collections2.isNotEmpty(effectiveTimeRange) && effectiveTimeRange.size() >= 2, FCI_EFFECTIVE_TIME_IS_EMPTY);

			LocalDateTime effectiveStartTime = effectiveTimeRange.get(0);
			LocalDateTime effectiveEndTime = effectiveTimeRange.get(1);

			Args.check(null != effectiveStartTime && null != effectiveEndTime, FCI_EFFECTIVE_TIME_IS_EMPTY);
			// 新增时，开始时间不能<=当前时间
			Args.check(effectiveStartTime.isAfter(LocalDateTime.now()), FCI_EFFECTIVE_START_TIME_AFTER_NOW);
			// 结束时间需要大于开始时间
			Args.check(effectiveEndTime.isAfter(effectiveStartTime), FCI_EFFECTIVE_END_TIME_AFTER_START);
		}


		//Args.checkNotBlank("", ResponseCode.ARGUMENT_ERROR);
		Method[] methods = Args.class.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}

		if (DataBindFlagEnum.BIND_DATA.is(getDataBindFlag())) {
			System.out.println(Args.class);
			Args.checkNotBlank(this.getDataUrl(), DATA_BIND_URL_IS_BLANK);
			Args.checkNotNull(this.getDataRequestMethod(), PROP_DATA_REQUEST_METHOD_IS_NULL);

			Args.checkNotBlank(propValueSelector, PROP_DATA_VALUE_SELECTOR_IS_BLANK);
		}

		return true;
	}
}
