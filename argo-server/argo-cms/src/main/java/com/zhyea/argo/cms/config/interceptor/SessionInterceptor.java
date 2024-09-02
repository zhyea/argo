package com.zhyea.argo.cms.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.chobit.commons.utils.StrKit.uuid;

/**
 * session信息拦截器
 *
 * @author robin
 */
public class SessionInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String sessionId = request.getSession(true).getId();
		System.out.println(Thread.currentThread().getId() + " ------------------BEGIN " + sessionId);
		return true;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {

		System.out.println(Thread.currentThread().getId() + " ------------------TEM  " + request.getRequestedSessionId());
	}


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		response.setHeader("sessionId", uuid());
		System.out.println(Thread.currentThread().getId() + " ------------------END  " + request.getRequestedSessionId());
	}
}
