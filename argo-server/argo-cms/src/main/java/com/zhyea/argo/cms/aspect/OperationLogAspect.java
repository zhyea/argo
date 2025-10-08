package com.zhyea.argo.cms.aspect;

import com.zhyea.argo.cms.annotation.OperationLog;
import com.zhyea.argo.tools.auth.AuthContext;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 操作日志切面
 * 拦截带有@OperationLog注解的方法，记录操作日志
 *
 * @author robin
 */
@Aspect
@Component
public class OperationLogAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);
    
    @Autowired
    private HttpServletRequest request;
    
    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.zhyea.argo.annotation.OperationLog)")
    public void operationLogPointCut() {
        // 切点定义
    }
    
    /**
     * 环绕通知，拦截方法执行前后，记录操作日志
     */
    @Around("operationLogPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取开始时间
        long startTime = System.currentTimeMillis();
        
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        
        // 获取注解信息
        OperationLog operationLog = method.getAnnotation(OperationLog.class);
        String type = operationLog.type();
        String description = operationLog.description();
        boolean recordParams = operationLog.recordParams();
        boolean recordResult = operationLog.recordResult();
        
        // 获取当前用户信息
        String username = AuthContext.getUsername();
        if (username == null) {
            username = "anonymous";
        }
        
        // 获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            request = attributes.getRequest();
        }
        String ip = getClientIp();
        String url = request.getRequestURI();
        String methodType = request.getMethod();
        
        // 记录请求参数
        Object[] args = joinPoint.getArgs();
        String params = "";
        if (recordParams && args != null && args.length > 0) {
            params = Arrays.toString(args);
        }
        
        // 记录操作前日志
        logger.info("[操作日志] 时间: {}, 用户: {}, IP: {}, 类型: {}, 描述: {}, URL: {}, 方法: {}, 参数: {}",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                username, ip, type, description, url, methodType, params);
        
        // 执行原方法
        Object result = joinPoint.proceed();
        
        // 记录返回结果
        String resultStr = "";
        if (recordResult && result != null) {
            resultStr = result.toString();
            // 限制日志长度，避免过大
            if (resultStr.length() > 500) {
                resultStr = resultStr.substring(0, 500) + "...";
            }
        }
        
        // 计算执行时间
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        
        // 记录操作后日志
        logger.info("[操作日志] 时间: {}, 用户: {}, IP: {}, 类型: {}, 描述: {}, URL: {}, 方法: {}, 执行时间: {}ms, 返回结果: {}",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                username, ip, type, description, url, methodType, executionTime, resultStr);
        
        return result;
    }
    
    /**
     * 获取客户端IP地址
     */
    private String getClientIp() {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 多级代理的情况下，第一个IP为客户端真实IP
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}