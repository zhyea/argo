package com.zhyea.argo.api.tools;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * HttpServletRequest工具类
 *
 * @author robin
 * @since 2025/9/19 20:32
 */
public final class ServletRequestKit {


	/**
	 * 获取请求头
	 *
	 * @param request 请求
	 * @return 请求头
	 */
	public static Map<String, String> getHeaders(HttpServletRequest request) {
		Map<String, String> headers = new HashMap<>(16);
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String name = headerNames.nextElement();
			headers.put(name, request.getHeader(name));
		}
		return headers;
	}


	/**
	 * 获取请求参数
	 *
	 * @param request 请求
	 * @return 请求参数
	 */
	public static Map<String, String> getParameters(HttpServletRequest request) {
		Map<String, String> parameters = new HashMap<>(16);
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = parameterNames.nextElement();
			parameters.put(name, request.getParameter(name));
		}
		return parameters;
	}


	private ServletRequestKit() {
	}

}
