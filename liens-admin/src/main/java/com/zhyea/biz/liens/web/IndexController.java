package com.zhyea.biz.liens.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author robin
 */
@RestController
@RequestMapping("")
public class IndexController {


    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }


    @GetMapping("")
    public String index() {
        return "ping";
    }

}
