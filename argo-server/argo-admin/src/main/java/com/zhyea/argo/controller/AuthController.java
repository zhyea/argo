package com.zhyea.argo.controller;

import com.zhyea.argo.action.UserLoginAction;
import com.zhyea.argo.model.request.UserLoginRequest;
import com.zhyea.argo.tools.AuthContext;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("")
public class AuthController {


    private final UserLoginAction loginAction;

    public AuthController(UserLoginAction loginAction) {
        this.loginAction = loginAction;
    }


    @PostMapping("/login")
    public String login(@Validated @RequestBody UserLoginRequest request) {
        return loginAction.doLogin(request.getUsername(), request.getPassword());
    }


    @PostMapping("/logout")
    public boolean logout() {
        AuthContext.clear();
        return true;
    }


    @RequestMapping("/ping")
    public String ping() {
        return "Medea";
    }


}
