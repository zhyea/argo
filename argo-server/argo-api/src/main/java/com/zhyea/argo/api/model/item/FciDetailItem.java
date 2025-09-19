package com.zhyea.argo.api.model.item;

import com.zhyea.argo.model.item.FciItem;
import lombok.Data;

import java.util.List;

/**
 * 组件信息
 *
 * @author robin
 * @since 2025/9/3 22:58
 */
@Data
public class FciDetailItem {


	/**
	 * 组件信息
	 */
	private FciItem fci;


	/**
	 * 组件属性信息
	 */
	private List<FciPropSimpleItem> fciPropList;


}
