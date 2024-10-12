package com.zhyea.argo.cms.model.entity;

import com.zhyea.argo.cms.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.cms.constants.enums.SwitchFlagEnum;
import com.zhyea.argo.model.entity.BaseEntity;
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
	 * 页面ID
	 */
	private Long pageId;


	/**
	 * 组件ID
	 */
	private Integer fcmId;


	/**
	 * 数据链接
	 */
	private String dataUrl;


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
