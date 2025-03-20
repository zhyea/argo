package com.zhyea.argo.data.entity.cms;

import com.zhyea.argo.data.entity.BaseEntity;
import lombok.Data;

/**
 * 页面FCI关联表
 *
 * @author robin
 */
@Data
public class PageFciEntity extends BaseEntity {


	/**
	 * 页面ID
	 */
	private Long pageId;


	/**
	 * FCI ID
	 */
	private Long fciId;


}
