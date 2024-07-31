package com.zhyea.argo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 默认api接口
 *
 * @author robin
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/ping")
    public String ping() {
        return "ping pong";
    }

}
