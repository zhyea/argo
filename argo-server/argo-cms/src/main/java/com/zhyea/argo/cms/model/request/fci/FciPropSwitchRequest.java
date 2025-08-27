package com.zhyea.argo.cms.model.request.fci;

import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import com.zhyea.argo.constants.enums.YesOrNo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chobit.commons.validation.EnumVal;


/**
 * 属性ID相关请求
 *
 * @author robin
 */
@Data
public class FciPropSwitchRequest extends BaseOperateRequest {


	/**
	 * 属性ID
	 */
	@NotNull(message = "属性Id不能为空")
	private Long propId;


	/**
	 * 属性开关标识
	 */
	@EnumVal(enumClass = YesOrNo.class, message = "属性开关标识值错误")
	@NotNull(message = "属性开关标识不能为空")
	private Integer switchFlag;


}
