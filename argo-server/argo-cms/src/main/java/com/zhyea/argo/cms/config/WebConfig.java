package com.zhyea.argo.cms.config;

import com.zhyea.argo.cms.config.interceptor.AuthInterceptor;
import com.zhyea.argo.cms.config.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 路由、拦截器等各项配置
 *
 * @author robin
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 读取session
        registry.addInterceptor(new SessionInterceptor());
        // 权限校验
        registry.addInterceptor(new AuthInterceptor())
                .excludePathPatterns("/login", "/logout");
    }


}
