package com.zhyea.argo.api.model.item;

import com.zhyea.argo.model.item.PageItem;
import lombok.Data;

import java.util.List;

/**
 * 页面详情
 *
 * @author robin
 * @since 2025/9/3 22:56
 */
@Data
public class PageDetailItem {


	/**
	 * 页面基础信息
	 */
	private PageItem page;


	/**
	 * 页面上的组件信息
	 */
	private List<FciDetailItem> fciList;


}
