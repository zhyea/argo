package com.zhyea.argo.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;

/**
 * Argo API 启动类
 *
 * @author robin
 */
//@ComponentScan("com.zhyea.argo")
@Slf4j
//@EnableCaching
//@SpringBootApplication
public class ArgoApiApplication {


	public static void main(String[] args) {

		SpringApplication.run(ArgoApiApplication.class, args);

		logger.info("应用 ARGO API 启动成功 ...... ");
	}


}
