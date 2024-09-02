package com.zhyea.argo.cms.controller;

import com.zhyea.argo.cms.model.request.UserLoginRequest;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.chobit.commons.utils.StrKit.uuid;


/**
 * 权限相关接口
 *
 * @author robin
 */
@RestController
@ResponseWrapper
public class AuthController {


    /**
     * 执行登录校验
     *
     * @param request 登录请求
     * @return token
     */
    @PostMapping("/login")
    public String login(@RequestBody @Valid UserLoginRequest request) {
        return uuid();
    }


}
