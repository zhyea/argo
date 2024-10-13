package com.zhyea.argo.cms.convert;

import org.chobit.commons.utils.JsonKit;
import org.mapstruct.Named;

import static org.chobit.commons.constans.Symbol.EMPTY;

/**
 * 转换器通用处理工具集合
 *
 * @author robin
 */
public final class CommonWorker {


	/**
	 * 转换为JSON字符串
	 *
	 * @param obj 对象
	 * @return JSON字符串
	 */
	@Named("toJson")
	public static String toJson(Object obj) {
		if (null == obj) {
			return EMPTY;
		}

		return JsonKit.toJson(obj);
	}
}
