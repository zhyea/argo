package com.zhyea.argo.biz.convert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhyea.argo.model.item.FcmPropItem;
import org.chobit.commons.utils.JsonKit;
import org.mapstruct.Named;

import java.util.Set;

import static org.chobit.commons.utils.StrKit.isBlank;

/**
 * 组件模型转换工具
 *
 * @author robin
 */
public final class FcmWorker {


	/**
	 * 将json字符串转换为属性集合
	 *
	 * @param propsJson 属性json字符串
	 * @return 属性集合
	 */
	@Named("toPropItems")
	public static Set<FcmPropItem> toPropItems(String propsJson) {
		if (isBlank(propsJson)) {
			return null;
		}
		return JsonKit.fromJson(propsJson, new TypeReference<Set<FcmPropItem>>() {
		});
	}


}
