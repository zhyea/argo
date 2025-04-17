package com.zhyea.argo.cms.model.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.constants.enums.SwitchFlagEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 组件实例信息
 *
 * @author robin
 */
@Data
public class FciItem {


	/**
	 * 组件ID
	 */
	private Long id;


	/**
	 * 组件名称
	 */
	private String name;


	/**
	 * 组件代码
	 */
	private String fciCode;


	/**
	 * 应用ID
	 */
	private Long appId;


	/**
	 * 模型ID
	 */
	private Long fcmId;


	/**
	 * 模型名称
	 */
	private String fcmName;


	/**
	 * 组件类型
	 */
	private Integer type;


	/**
	 * 数据绑定标记
	 */
	private Integer dataBindFlag;


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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime effectiveStartTime;


	/**
	 * 组件生效结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime effectiveEndTime;


	/**
	 * 备注
	 */
	private String remark;

}
