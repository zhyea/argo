package com.zhyea.argo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Index接口
 *
 * @author robin
 */
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
