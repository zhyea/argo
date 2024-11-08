package com.zhyea.argo.cms.model.request.fci;

import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import com.zhyea.argo.constants.enums.EffectivePeriodTypeEnum;
import lombok.Data;
import org.chobit.commons.validation.EnumVal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 组件实例新增请求
 *
 * @author robin
 */
@Data
public class FciAddRequest extends BaseOperateRequest {


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
