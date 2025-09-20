package com.zhyea.argo.model.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.constants.enums.SwitchFlagEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 组件属性信息
 *
 * @author robin
 */
@Data
public class FciPropItem extends BaseDataBindReq {


	/**
	 * 记录ID
	 */
	private Long id;


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
	 * 数据绑定标记
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
	 * 删除标记
	 */
	private Integer deleted;


	/**
	 * 描述
	 */
	private String remark;


	/**
	 * 状态
	 */
	private Integer status;


	/**
	 * 状态描述
	 */
	private String statusDesc;


	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;



	private Integer fciDataBindFlag;

}
