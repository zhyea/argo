package com.zhyea.argo.controller;

import com.zhyea.argo.cms.annotation.OperationLog;
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


	@OperationLog(type = "用户管理", description = "添加用户", recordParams = true, recordResult = true)
	@PostMapping("/add")
	public Long add(@RequestBody @Validated UserAddRequest request) {
		return userService.addUser(request);
	}


	@OperationLog(type = "用户管理", description = "更新用户信息", recordParams = true, recordResult = true)
	@PostMapping("/update")
	public boolean update(@RequestBody @Validated UserUpdateRequest request) {
		userService.updateUser(request);
		return true;
	}


	@OperationLog(type = "用户管理", description = "删除用户", recordParams = true, recordResult = true)
	@PostMapping("/delete")
	public boolean delete(@RequestParam Long id) {
		userService.deleteUser(id);
		return true;
	}


	@OperationLog(type = "用户管理", description = "查询用户列表", recordParams = true, recordResult = false)
	@PostMapping("/list")
	public PageResponse<UserEntity> list(@RequestBody @Validated UserListQueryRequest request) {
		return userService.getUserList(request.getKeyword(), request.getPageNo(), request.getPageSize());
	}


	@OperationLog(type = "用户管理", description = "查询用户详情", recordParams = true, recordResult = false)
	@GetMapping("/detail")
	public UserEntity detail(@RequestParam Long id) {
		return userService.getUserById(id);
	}


	@OperationLog(type = "用户管理", description = "修改密码", recordParams = false, recordResult = true)
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


	@OperationLog(type = "用户偏好", description = "设置最后访问应用", recordParams = true, recordResult = true)
	@PostMapping("/last-visit-app/set")
	public boolean setLastVisitApp(@RequestBody @Validated AppIdRelateRequest request) {
		return userOptionsService.setLastVisitApp(request.getAppId());
	}


	@OperationLog(type = "用户偏好", description = "获取最后访问应用", recordParams = false, recordResult = true)
	@GetMapping("/last-visit-app")
	public Long getLastVisitApp() {
		return userOptionsService.getLastVisitApp();
	}


}
