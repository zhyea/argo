package com.zhyea.argo.model.request.fcm;

import com.zhyea.argo.model.request.BaseOperateRequest;
import lombok.Data;

import jakarta.validation.constraints.NotNull;


/**
 * 组件模型ID相关请求
 *
 * @author robin
 */
@Data
public class FcmIdRelateRequest extends BaseOperateRequest {


	/**
	 * 组件模型ID
	 */
	@NotNull(message = "组件模型Id不能为空")
	private Long fcmId;


}
