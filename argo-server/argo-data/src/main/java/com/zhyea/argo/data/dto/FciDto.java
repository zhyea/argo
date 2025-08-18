package com.zhyea.argo.data.dto;

import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.constants.enums.SwitchFlagEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 组件数据
 *
 * @author robin
 * @since 2025/4/7 22:59
 */
@Data
public class FciDto {


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

	
	/**
	 * 操作人code
	 */
	private String operatorCode;


	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;


	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

}
