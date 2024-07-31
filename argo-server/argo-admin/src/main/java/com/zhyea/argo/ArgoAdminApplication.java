package com.zhyea.argo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhyea.argo.tools.BeanKit;
import org.chobit.commons.json.LocalDateTimeModule;
import org.chobit.commons.utils.JsonKit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Liens Admin 启动类
 *
 * @author robin
 */
@EnableCaching
@SpringBootApplication
public class ArgoAdminApplication {


    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(ArgoAdminApplication.class, args);

        BeanKit.init(ctx);

        System.out.println("应用启动成功...");
    }

}
