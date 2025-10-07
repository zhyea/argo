package com.zhyea.argo.service;

import com.zhyea.argo.data.entity.adm.UserEntity;
import com.zhyea.argo.data.mapper.adm.UserMapper;
import com.zhyea.argo.model.request.UserAddRequest;
import com.zhyea.argo.model.request.UserUpdateRequest;
import com.zhyea.argo.model.response.PageResponse;
import com.zhyea.argo.tools.Args;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.zhyea.argo.constants.ResponseCode.*;


/**
 * 用户信息处理
 *
 * @author robin
 */
@Slf4j
@Service
public class UserService {


	private final UserMapper userMapper;


	@Autowired
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}


	/**
	 * 根据登录信息查询用户
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户信息
	 */
	public UserEntity getByUserPwd(String username, String password) {
		return userMapper.getByUserAndPassword(username, password);
	}


	/**
	 * 新增用户信息
	 *
	 * @param req 用户信息
	 * @return 用户记录ID
	 */
	public Long addUser(UserAddRequest req) {

		Args.checkEquals(req.getPassword(), req.getRePassword(), CONFIRM_PASSWORD_NOT_MATCH);
		Args.checkNotNull(userMapper.getByUsername(req.getUsername()), USERNAME_ALREADY_EXISTS);

		UserEntity user = new UserEntity();
		user.setUsername(req.getUsername());
		user.setPassword(req.getPassword());
		user.setNickName(req.getNickname());

		userMapper.add(user);

		return user.getId();
	}

	/**
	 * 获取用户列表
	 *
	 * @param keyword 关键字
	 * @param page    页码
	 * @param size    每页大小
	 * @return 分页用户列表
	 */
	public PageResponse<UserEntity> getUserList(String keyword, int page, int size) {
		int offset = (page - 1) * size;
		List<UserEntity> userList = userMapper.findList(keyword, offset, size);
		long total = userMapper.count(keyword);
		return new PageResponse<>(userList, total, page, size);
	}

	/**
	 * 根据ID获取用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	public UserEntity getUserById(Long id) {
		UserEntity user = userMapper.getById(id);
		Args.checkNotNull(user, USER_NOT_FOUND);
		return user;
	}

	/**
	 * 更新用户信息
	 *
	 * @param req 用户更新信息
	 */
	public void updateUser(UserUpdateRequest req) {
		UserEntity user = getUserById(req.getId());
		
		// 检查用户名是否已存在（排除当前用户）
		UserEntity existingUser = userMapper.getByUsername(req.getUsername());
		Args.check(!(existingUser != null && !existingUser.getId().equals(req.getId())), USERNAME_ALREADY_EXISTS);

		user.setUsername(req.getUsername());
		user.setNickName(req.getNickname());
		userMapper.update(user);
	}

	/**
	 * 删除用户
	 *
	 * @param id 用户ID
	 */
	public void deleteUser(Long id) {
		getUserById(id); // 检查用户是否存在
		userMapper.delete(id);
	}

	/**
	 * 修改密码
	 *
	 * @param id          用户ID
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param confirmPassword 确认密码
	 */
	public void changePassword(Long id, String oldPassword, String newPassword, String confirmPassword) {
		Args.checkEquals(newPassword, confirmPassword, CONFIRM_PASSWORD_NOT_MATCH);
		
		UserEntity user = getUserById(id);
		Args.checkEquals(user.getPassword(), oldPassword, CURRENT_PASSWORD_ERROR);

		userMapper.updatePassword(id, newPassword);
	}

	/**
	 * 重置登录时间
	 *
	 * @param username 用户名
	 */
	public void resetLoginTime(String username) {
		userMapper.resetLoginTime(username, LocalDateTime.now());
	}

}
