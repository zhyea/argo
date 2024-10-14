package com.zhyea.argo.cms;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * Argo CMS 启动类
 *
 * @author robin
 */
@ComponentScan("com.zhyea.argo")
@Slf4j
@EnableCaching
@SpringBootApplication
public class ArgoCmsApplication {


	public static void main(String[] args) {

		SpringApplication.run(ArgoCmsApplication.class, args);

		logger.info("应用 ARGO CMS 启动成功 ...... ");
	}


}
