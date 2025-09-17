package com.zhyea.argo.cms.model.item;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import com.zhyea.argo.constants.enums.SwitchFlagEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
	 * 组件使用范围
	 */
	private Integer usageScope;


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
	 * 数据绑定标记
	 */
	private Integer dataBindFlag;


	/**
	 * 数据链接
	 */
	private String dataUrl;


	/**
	 * 数据请求方式
	 */
	private Integer dataRequestMethod;


	/**
	 * 数据请求参数
	 */
	private String dataRequestParams;


	/**
	 * 数据请求头
	 */
	private String dataRequestHeaders;


	/**
	 * 属性值选择器
	 */
	private String propValueSelector;


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


	/**
	 * 操作人代码
	 */
	private String operatorCode;


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


	/**
	 * 是否存在属性继承了组件实例的配置
	 */
	private boolean existsInheritedProps;


	/**
	 * 使用组件实例的页面列表
	 */
	private List<PageItem> pageList;

}
