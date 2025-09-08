package com.zhyea.argo.data.entity.cms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.constants.enums.SwitchFlagEnum;
import com.zhyea.argo.data.entity.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 组件属性
 *
 * @author robin
 */
@Data
public class FciPropEntity extends BaseEntity {


	/**
	 * 组件实例ID
	 */
	private Long fciId;


	/**
	 * 属性key
	 */
	private String propKey;


	/**
	 * 属性值
	 */
	private String propValue;


	/**
	 * 数据绑定标识
	 */
	private Integer dataBindFlag;




	/**
	 * 属性值选择器
	 */
	private String propValueSelector;


	/**
	 * 开关标记
	 * <p>
	 * see also {@link SwitchFlagEnum}
	 */
	private Integer switchFlag;


	/**
	 * 属性生效周期类型
	 * <p>
	 * see also {@link EffectivePeriodTypeEnum}
	 */
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

}
