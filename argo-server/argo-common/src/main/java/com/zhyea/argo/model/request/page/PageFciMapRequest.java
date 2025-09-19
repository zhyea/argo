package com.zhyea.argo.model.request.page;

import com.zhyea.argo.model.request.BaseOperateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 页面FCI关联请求
 *
 * @author robin
 * @since 2025/3/20 7:43
 */
@Data
public class PageFciMapRequest extends BaseOperateRequest {


	/**
	 * 页面ID
	 */
	@NotNull(message = "页面Id不能为空")
	private Long pageId;


	/**
	 * 组件ID集合
	 */
	@NotNull(message = "组件ID不能为空")
	private List<Long> fciIdList;
}
