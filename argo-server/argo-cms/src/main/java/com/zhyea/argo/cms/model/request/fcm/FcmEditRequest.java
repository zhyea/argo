package com.zhyea.argo.cms.model.request.fcm;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 组件模型更新请求
 *
 * @author robin
 */
@Data
public class FcmEditRequest extends FcmAddRequest {


	/**
	 * 组件模型ID
	 */
	@NotNull(message = "组件模型ID不能为空")
	private Long fcmId;

}
