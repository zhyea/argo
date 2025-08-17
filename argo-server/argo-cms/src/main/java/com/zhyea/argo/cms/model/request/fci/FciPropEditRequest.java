package com.zhyea.argo.cms.model.request.fci;

import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import com.zhyea.argo.except.ArgoServerException;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chobit.commons.exception.ParamException;

import java.time.LocalDateTime;

import static com.zhyea.argo.constants.ResponseCode.*;
import static org.chobit.commons.utils.StrKit.isBlank;


/**
 * FCI属性修改请求
 *
 * @author robin
 */
@Data
public class FciPropEditRequest extends FciPropAddRequest {


	/**
	 * 属性Id
	 */
	@NotNull(message = "属性Id不能为空")
	private Long id;


	@Override
	public boolean check() throws ParamException {

		if (YesOrNo.YES.is(getDataBindFlag()) && isBlank(getPropValueSelector())) {
			throw new ArgoServerException(DATA_BIND_URL_IS_EMPTY);
		}

		if (EffectivePeriodTypeEnum.FIXED_TERM.is(getEffectivePeriodType())) {
			LocalDateTime effectiveStartTime = getEffectiveStartTime();
			LocalDateTime effectiveEndTime = getEffectiveEndTime();

			if (null == effectiveStartTime || null == effectiveEndTime) {
				throw new ArgoServerException(FCI_PROP_EFFECTIVE_TIME_IS_EMPTY);
			}

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
