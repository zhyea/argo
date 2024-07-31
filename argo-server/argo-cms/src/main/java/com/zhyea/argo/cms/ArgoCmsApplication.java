package com.zhyea.argo.cms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Argo CMS 启动类
 *
 * @author robin
 */
@EnableCaching
@SpringBootApplication
public class ArgoCmsApplication {


    public static void main(String[] args) {

        SpringApplication.run(ArgoCmsApplication.class, args);

        System.out.println("应用启动成功...");
    }



}
