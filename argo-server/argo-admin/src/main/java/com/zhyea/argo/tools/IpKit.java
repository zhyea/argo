package com.zhyea.argo.tools;

import jakarta.servlet.http.HttpServletRequest;

import static com.zhyea.argo.constants.SystemConstants.UNKNOWN;
import static org.chobit.commons.utils.StrKit.isBlank;

/**
 * IP 工具类
 *
 * @author robin
 * @since 2025/5/11 22:50
 */
public final class IpKit {


	/**
	 * 获取客户端IP
	 *
	 * @param request 请求信息
	 * @return ip
	 */
	public static String clientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}


}
