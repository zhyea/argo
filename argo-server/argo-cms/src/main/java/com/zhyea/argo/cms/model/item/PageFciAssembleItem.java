package com.zhyea.argo.cms.model.item;

import lombok.Data;

import java.util.List;

/**
 * 页面组件组装项
 *
 * @author robin
 * @since 2025/8/31 23:48
 */
@Data
public class PageFciAssembleItem {


	private Integer pageId;


	private List<FciItem> availableFciList;


	private List<FciItem> usedFciList;
}
