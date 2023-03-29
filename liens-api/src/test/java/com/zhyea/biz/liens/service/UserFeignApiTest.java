package com.zhyea.biz.liens.service;

import com.zhyea.biz.liens.TestBase;
import com.zhyea.biz.liens.common.entity.User;
import com.zhyea.biz.liens.service.feign.UserFeignApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserFeignApiTest extends TestBase {


    @Autowired
    private UserFeignApi userFeignApi;


    @Test
    public void get(){
        User user = userFeignApi.get(10010L);
        System.out.println(user);
        Assertions.assertNotNull(user);
    }



}
