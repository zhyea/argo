package com.zhyea.argo.api.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 查询FCI详情
 *
 * @author robin
 * @since 2025/9/20 17:56
 */
@Data
public class FciDetailRequest {

	/**
	 * FCI编码
	 */
	@NotNull(message = "FCI编码不能为空")
	@NotBlank(message = "FCI编码不能为空")
	private String fciCode;

}
