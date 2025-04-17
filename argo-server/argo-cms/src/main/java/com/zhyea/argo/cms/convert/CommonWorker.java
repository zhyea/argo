package com.zhyea.argo.cms.convert;

import org.chobit.commons.utils.Collections2;
import org.chobit.commons.utils.JsonKit;
import org.chobit.commons.utils.LocalDateKit;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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


	private static final ZoneId ZONE_ID_0 = ZoneId.of("+00:00");
	private static final ZonedDateTime BASE_TIME
			= ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZONE_ID_0);

	/**
	 * 忽略无效的时间
	 *
	 * @param time 时间
	 * @return 时间
	 */
	@Named("ignoreInvalidTime")
	public static LocalDateTime ignoreInvalidTime(LocalDateTime time) {
		if (null == time) {
			return null;
		}
		ZonedDateTime zonedDateTime = ZonedDateTime.of(time, ZONE_ID_0);
		System.out.println(LocalDateKit.formatTime(time));

		if (BASE_TIME.equals(zonedDateTime)) {
			return null;
		}
		return time;
	}


}
