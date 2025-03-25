package com.zhyea.argo.cms.model.request.fci;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import com.zhyea.argo.except.ArgoServerException;
import lombok.Data;
import org.chobit.commons.contract.Checkable;
import org.chobit.commons.exception.ParamException;
import org.chobit.commons.utils.Collections2;
import org.chobit.commons.validation.EnumVal;
import org.chobit.commons.validation.WholeCheck;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class FciAddRequest extends BaseOperateRequest implements Checkable {

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
	@EnumVal(enumClass = YesOrNo.class, message = "开关标志错误")
	private Integer switchFlag;


	/**
	 * 数据链接
	 */
	private String dataUrl;


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
			if (Collections2.isEmpty(effectiveTimeRange) || effectiveTimeRange.size() < 2) {
				throw new ArgoServerException(FCI_PROP_EFFECTIVE_TIME_IS_EMPTY);
			}

			LocalDateTime effectiveStartTime = effectiveTimeRange.get(0);
			LocalDateTime effectiveEndTime = effectiveTimeRange.get(1);

			// 新增时，开始时间不能<=当前时间
			if (effectiveStartTime.isBefore(LocalDateTime.now())
					|| effectiveStartTime.isEqual(LocalDateTime.now())) {
				throw new ArgoServerException(FCI_PROP_EFFECTIVE_START_TIME_AFTER_NOW);
			}

			// 结束时间需要大于开始时间
			if (!effectiveEndTime.isAfter(effectiveStartTime)) {
				throw new ArgoServerException(FCI_PROP_EFFECTIVE_END_TIME_AFTER_START);
			}
		}

		return true;
	}
}
