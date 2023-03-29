package com.zhyea.biz.liens.ek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author robin
 */
@EnableEurekaServer
@SpringBootApplication
public class LiensEurekaApplication {


    public static void main(String[] args) {
        SpringApplication.run(LiensEurekaApplication.class, args);
    }

}
