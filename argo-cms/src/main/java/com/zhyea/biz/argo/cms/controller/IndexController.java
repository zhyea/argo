package com.zhyea.biz.argo.cms.controller;

import com.zhyea.biz.argo.cms.config.ResponseWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Index接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("")
public class IndexController {


    @GetMapping("/ping")
    public String ping() {
        return "ping pong";
    }


    @GetMapping("")
    public String index() {
        return "Welcome！";
    }

}