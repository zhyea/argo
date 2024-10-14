package com.zhyea.argo.cms.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.zhyea.argo.constants.SystemConstants.SESSION_ID;
import static org.chobit.commons.utils.StrKit.isBlank;

/**
 * session信息拦截器
 *
 * @author robin
 */
@Slf4j
public class SessionInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 通过调用 request.getSession(true) 创建sessionId
        String sessionId = request.getSession(true).getId();
        if (isBlank(MDC.get(SESSION_ID))) {
            MDC.put(SESSION_ID, sessionId);
        }
        logger.info("{} ------------------BEGIN {}, {}", Thread.currentThread().getId(), sessionId, request.getRequestedSessionId());
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

        logger.info("{} ------------------TEM {}, {}", Thread.currentThread().getId(), request.getSession().getId(), request.getRequestedSessionId());
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        logger.info("{} ------------------END {}, {}", Thread.currentThread().getId(), request.getSession().getId(), request.getRequestedSessionId());
    }
}
