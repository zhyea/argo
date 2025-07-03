package com.zhyea.argo.cms.model.request.app;

import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import com.zhyea.argo.constants.enums.AppTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chobit.commons.validation.EnumVal;


/**
 * 新增应用请求
 *
 * @author robin
 */
@Data
public class AppAddRequest extends BaseOperateRequest {


	/**
	 * 应用ID
	 */
	@NotBlank(message = "应用ID不能为空")
	private String appCode;


	/**
	 * 应用名称
	 */
	@NotBlank(message = "应用名称不能为空")
	private String appName;


	/**
	 * 应用类型
	 */
	@EnumVal(enumClass = AppTypeEnum.class, message = "应用类型错误")
	@NotNull(message = "应用类型不能为空")
	private Integer appType;

	/**
	 * 应用图标
	 */
	private String icon;


	/**
	 * 应用描述
	 */
	private String remark;


}
