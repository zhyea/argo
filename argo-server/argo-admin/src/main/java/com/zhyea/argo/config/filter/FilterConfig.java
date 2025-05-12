package com.zhyea.argo.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 *
 * @author robin
 * @since 2025/5/11 22:45
 */
@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<ContentCachingFilter> loggingFilter() {
		FilterRegistrationBean<ContentCachingFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new ContentCachingFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
}
