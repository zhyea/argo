package com.zhyea.argo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author robin
 */
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class ArgoApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(ArgoApiApplication.class, args);
    }
}
