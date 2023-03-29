package com.zhyea.biz.liens.spring;


import com.zhyea.biz.liens.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ScheduleConfig {


    private final UserService userService;

    @Autowired
    public ScheduleConfig(UserService userService) {
        this.userService = userService;
    }


    @Scheduled(cron = "0 0/5 * * * ? ")
    public void reInform() {
        userService.reInform();
    }
}
