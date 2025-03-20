package com.zhyea.argo.cms.model.request.page;

import com.zhyea.argo.cms.model.request.BaseOperateRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
	@NotEmpty(message = "组件ID不能为空")
	private List<Long> fciIdList;
}
