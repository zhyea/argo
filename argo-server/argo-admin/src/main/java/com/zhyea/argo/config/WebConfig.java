package com.zhyea.argo.config;

import com.zhyea.argo.config.intercept.AuthInterceptor;
import com.zhyea.argo.config.intercept.SessionInterceptor;
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

        registry.addInterceptor(new SessionInterceptor());

        registry.addInterceptor(new AuthInterceptor())
                .excludePathPatterns("/api/**", "/login", "/logout", "/favicon.ico");
    }


}
