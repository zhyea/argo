package com.zhyea.argo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP配置类
 * 启用AspectJ自动代理功能
 *
 * @author robin
 */
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    
    // AOP配置类，通过@EnableAspectJAutoProxy注解启用AspectJ自动代理
    // 这样Spring容器会自动检测并处理带有@Aspect注解的Bean
}