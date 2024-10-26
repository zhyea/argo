package com.zhyea.argo.service;

import com.zhyea.argo.data.entity.adm.UserEntity;
import com.zhyea.argo.data.mapper.adm.UserMapper;
import com.zhyea.argo.model.request.UserAddRequest;
import com.zhyea.argo.tools.Args;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.zhyea.argo.constants.ResponseCode.CONFIRM_PASSWORD_NOT_MATCH;


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

		UserEntity user = new UserEntity();
		user.setUsername(req.getUsername());
		user.setPassword(req.getPassword());
		user.setNickName(req.getNickname());

		userMapper.add(user);

		return user.getId();
	}

}
