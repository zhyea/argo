package com.zhyea.argo;

import com.zhyea.argo.tools.BeanKit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Argo Admin 启动类
 *
 * @author robin
 */
@EnableTransactionManagement
@EnableCaching
@SpringBootApplication
public class ArgoAdminApplication {


	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(ArgoAdminApplication.class, args);

		BeanKit.init(ctx);

		System.out.println("应用启动成功...");
	}

}
