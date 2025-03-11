package com.zhyea.argo.cms.model.request.fci;

import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
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

/**
 * 组件实例新增请求
 *
 * @author robin
 */
@WholeCheck(message = "组件实例信息有误")
@Data
public class FciAddRequest extends BaseOperateRequest implements Checkable {


	/**
	 * 页面ID
	 */
	@NotNull(message = "pageId不能为空")
	private Long pageId;


	/**
	 * 组件模型ID
	 */
	@NotNull(message = "fcmId不能为空")
	private Integer fcmId;


	/**
	 * 组件名称
	 */
	@NotBlank(message = "name不能为空")
	private String name;


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
	List<LocalDateTime> effectiveTimeRange;


	/**
	 * 备注
	 */
	private String remark;


	@Override
	public boolean check() throws ParamException {
		if (EffectivePeriodTypeEnum.FIXED_TERM.is(effectivePeriodType)) {
			if (Collections2.isEmpty(effectiveTimeRange) || effectiveTimeRange.size() < 2) {
				return false;
			}

			LocalDateTime effectiveStartTime = effectiveTimeRange.get(0);
			LocalDateTime effectiveEndTime = effectiveTimeRange.get(1);

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

		return true;
	}
}
