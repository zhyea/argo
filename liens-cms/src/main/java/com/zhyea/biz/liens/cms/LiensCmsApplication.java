package com.zhyea.biz.liens.cms;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.chobit.commons.json.LocalDateTimeModule;
import org.chobit.commons.utils.JsonKit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Liens CMS 启动类
 *
 * @author rui.zhang
 */
@EnableCaching
@SpringBootApplication
public class LiensCmsApplication {


    public static void main(String[] args) {
        prepare();

        SpringApplication.run(LiensCmsApplication.class, args);

        System.out.println("应用启动成功...");
    }


    private static void prepare() {
        JsonKit.mapper().registerModule(new LocalDateTimeModule());
    }


    @Bean
    public ObjectMapper mapper() {
        ObjectMapper mapper = JsonKit.mapper();

        mapper.registerModule(new LocalDateTimeModule());

        return mapper;
    }


}
