package com.zhyea.argo.controller;

import com.zhyea.argo.data.entity.adm.UserEntity;
import com.zhyea.argo.model.request.app.AppIdRelateRequest;
import com.zhyea.argo.model.request.PasswordChangeRequest;
import com.zhyea.argo.model.request.UserAddRequest;
import com.zhyea.argo.model.request.UserListQueryRequest;
import com.zhyea.argo.model.request.UserUpdateRequest;
import com.zhyea.argo.model.response.PageResponse;
import com.zhyea.argo.service.UserOptionsService;
import com.zhyea.argo.service.UserService;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
	private final UserOptionsService userOptionsService;


	@Autowired
	public UserController(UserService userBiz, UserOptionsService userOptionsService) {
		this.userService = userBiz;
		this.userOptionsService = userOptionsService;
	}


	@PostMapping("/add")
	public Long add(@RequestBody @Validated UserAddRequest request) {
		return userService.addUser(request);
	}


	@PostMapping("/update")
	public boolean update(@RequestBody @Validated UserUpdateRequest request) {
		userService.updateUser(request);
		return true;
	}


	@PostMapping("/delete")
	public boolean delete(@RequestParam Long id) {
		userService.deleteUser(id);
		return true;
	}


	@PostMapping("/list")
	public PageResponse<UserEntity> list(@RequestBody @Validated UserListQueryRequest request) {
		return userService.getUserList(request.getKeyword(), request.getPageNo(), request.getPageSize());
	}


	@GetMapping("/detail")
	public UserEntity detail(@RequestParam Long id) {
		return userService.getUserById(id);
	}


	@PostMapping("/change-password")
	public boolean changePassword(@RequestBody @Validated PasswordChangeRequest request) {
		userService.changePassword(
				request.getId(), 
				request.getOldPassword(), 
				request.getNewPassword(), 
				request.getConfirmPassword()
		);
		return true;
	}


	@PostMapping("/last-visit-app/set")
	public boolean setLastVisitApp(@RequestBody @Validated AppIdRelateRequest request) {
		return userOptionsService.setLastVisitApp(request.getAppId());
	}


	@GetMapping("/last-visit-app")
	public Long getLastVisitApp() {
		return userOptionsService.getLastVisitApp();
	}


}
