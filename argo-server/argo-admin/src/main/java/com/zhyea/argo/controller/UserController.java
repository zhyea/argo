package com.zhyea.argo.controller;

import com.zhyea.argo.model.request.UserAddRequest;
import com.zhyea.argo.service.UserService;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户信息相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/admin/user")
public class UserController {


	private final UserService userService;


	@Autowired
	public UserController(UserService userBiz) {
		this.userService = userBiz;
	}


	@PostMapping("/add")
	public Long add(@RequestBody @Validated UserAddRequest request) {
		return userService.addUser(request);
	}


}
