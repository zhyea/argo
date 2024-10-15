package com.zhyea.argo.action;

import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.entity.adm.UserEntity;
import com.zhyea.argo.except.ArgoAdminException;
import com.zhyea.argo.mapper.adm.UserMapper;
import com.zhyea.argo.model.item.UserItem;
import com.zhyea.argo.service.UserService;
import com.zhyea.argo.tools.AuthContext;
import org.chobit.commons.codec.MD5;
import org.chobit.commons.utils.LocalDateKit;
import org.chobit.commons.utils.StrKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.chobit.commons.constans.Symbol.COMMA;

/**
 * 用户相关业务处理
 *
 * @author robin
 */
@Component
public class UserLoginAction {


	private final UserService userService;

	private final UserMapper userMapper;

	@Autowired
	public UserLoginAction(UserService userService, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}


	@Value("${argo.password.salt}")
	private String pwdSalt;


	/**
	 * 用户登录检查
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @return true 登陆成功， false 登录失败
	 */
	public String doLogin(String username, String password) {

		// 密码加盐后查询记录校验
		password = MD5.encode(password + pwdSalt);
		UserEntity user = userService.getByUserPwd(username, password);

		if (null == user) {
			throw new ArgoAdminException(ResponseCode.USER_LOGIN_INFO_ERROR);
		}

		// 重置上次登录时间
		LocalDateTime now = LocalDateTime.now();
		userMapper.resetLoginTime(username, now);

		// 使用用户名、密码、登录时间生成token
		String authInfo = StrKit.join(COMMA, username, password, LocalDateKit.formatTime(now));
		String token = MD5.encode(authInfo);

		// 将登录信息置于AuthContext
		UserItem userItem =
				UserItem.builder().username(username).token(token).lastLoginTime(now).build();
		AuthContext.addUser(userItem);

		return token;
	}


}
