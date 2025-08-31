package com.zhyea.argo.data.entity.cms;

import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.constants.enums.SwitchFlagEnum;
import com.zhyea.argo.data.entity.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 组件实例
 *
 * @author robin
 */
@Data
public class FciEntity extends BaseEntity {

	/**
	 * 应用ID
	 */
	private Long appId;

	/**
	 * 组件ID
	 */
	private Long fcmId;

	/**
	 * 组件实例编码
	 */
	private String fciCode;

	/**
	 * 组件使用范围
	 */
	private Integer usageScope;

	/**
	 * 组件名称
	 */
	private String name;

	/**
	 * 开关标记
	 * <p>
	 * see also {@link SwitchFlagEnum}
	 */
	private Integer switchFlag;

	/**
	 * 组件生效周期类型
	 * <p>
	 * see also {@link EffectivePeriodTypeEnum}
	 */
	private Integer effectivePeriodType;

	/**
	 * 组件生效开始时间
	 */
	private LocalDateTime effectiveStartTime;

	/**
	 * 组件生效结束时间
	 */
	private LocalDateTime effectiveEndTime;

	/**
	 * 备注
	 */
	private String remark;
}
