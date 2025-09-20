package com.zhyea.argo.data.entity.cms;

import com.zhyea.argo.constants.enums.SwitchFlagEnum;
import com.zhyea.argo.data.entity.BaseEntity;
import lombok.Data;

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
	 * 数据绑定标识
	 */
	private Integer dataBindFlag;

	/**
	 * 备注
	 */
	private String remark;
}
