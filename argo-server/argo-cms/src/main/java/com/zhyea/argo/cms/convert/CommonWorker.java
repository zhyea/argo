package com.zhyea.argo.cms.convert;

import org.chobit.commons.utils.Collections2;
import org.chobit.commons.utils.JsonKit;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.util.List;

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


	/**
	 * 获取时间范围中的开始时间
	 *
	 * @param timeRange 时间范围（包含两个值）
	 * @return 开始时间
	 */
	@Named("takeStartTime")
	public static LocalDateTime takeStartTime(List<LocalDateTime> timeRange) {
		if (Collections2.isEmpty(timeRange)) {
			return null;
		}

		return timeRange.get(0);
	}


	/**
	 * 获取时间范围中的结束时间
	 *
	 * @param timeRange 时间范围（包含两个值）
	 * @return 结束时间
	 */
	@Named("takeEndTime")
	public static LocalDateTime takeEndTime(List<LocalDateTime> timeRange) {
		if (Collections2.isEmpty(timeRange) || timeRange.size() < 2) {
			return null;
		}

		return timeRange.get(1);
	}

}
