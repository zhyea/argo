package com.zhyea.argo.action;

import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.data.entity.adm.UserEntity;
import com.zhyea.argo.data.mapper.adm.UserMapper;
import com.zhyea.argo.except.ArgoServerException;
import com.zhyea.argo.service.UserService;
import com.zhyea.argo.tools.auth.AES;
import com.zhyea.argo.tools.auth.AuthContext;
import com.zhyea.argo.tools.auth.AuthInfo;
import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.codec.MD5;
import org.chobit.commons.utils.DateKit;
import org.chobit.commons.utils.JsonKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.chobit.commons.utils.StrKit.isBlank;
import static org.chobit.commons.utils.StrKit.isNotBlank;

/**
 * 用户相关业务处理
 *
 * @author robin
 */
@Slf4j
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
	@Value("${argo.auth.key}")
	private String authKey;
	@Value("${argo.auth.iv}")
	private String authIv;
	@Value("${argo.auth.token-duration-minutes}")
	private int tokenDurationMinutes;
	@Value("${argo.auth.idle-timeout-minutes}")
	private int idleTimeoutMinutes;
	@Value("${argo.auth.strict-check}")
	private boolean strictCheck;


	/**
	 * 用户登录检查
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @return true 登陆成功， false 登录失败
	 */
	@Transactional
	public String doLogin(String username, String password) throws Exception {

		// 密码加盐后查询记录校验
		password = MD5.encode(password + pwdSalt);
		UserEntity user = userService.getByUserPwd(username, password);

		if (null == user) {
			throw new ArgoServerException(ResponseCode.USER_LOGIN_INFO_ERROR);
		}

		// 重置上次登录时间
		LocalDateTime now = LocalDateTime.now();
		userMapper.resetLoginTime(username, now);

		return refreshToken(username, password);
	}


	public void checkToken(String token, String ip) throws Exception {

		if (isNotBlank(AuthContext.getClientIp()) && !AuthContext.getClientIp().equals(ip)) {
			logger.info("UserLoginAction#decodeToken origenIp:{}, currentIp:{}", ip, AuthContext.getClientIp());
			AuthContext.clear();
			throw new ArgoServerException(ResponseCode.CLIENT_IP_CHANGED_ERROR);
		}

		String json = AES.decrypt(token, authKey, authIv);
		AuthInfo authInfo = JsonKit.fromJson(json, AuthInfo.class);
		if (null == authInfo) {
			logger.warn("UserLoginAction#decodeToken invalid token:{}", token);
			throw new ArgoServerException(ResponseCode.INVALID_TOKEN_ERROR);
		}

		if (strictCheck) {
			UserEntity user = userService.getByUserPwd(authInfo.getUsername(), authInfo.getPassword());
			if (null == user) {
				logger.warn("UserLoginAction#decodeToken invalid user info:{}", authInfo.getUsername());
				throw new ArgoServerException(ResponseCode.INVALID_TOKEN_ERROR);
			}
		}

		long expireTime = authInfo.getExpireTime();

		long timeLeft = expireTime - System.currentTimeMillis();
		if (timeLeft < 0) {
			logger.info("UserLoginAction#checkToken 用户长时间没有操作，已自动登出 token过期时间:{}", DateKit.format(new Date(expireTime)));
			throw new ArgoServerException(ResponseCode.EXPIRED_TOKEN_ERROR);
		}

		if (timeLeft < TimeUnit.MINUTES.toMillis(idleTimeoutMinutes)) {
			refreshToken(authInfo.getUsername(), authInfo.getPassword());
		} else if (isBlank(AuthContext.getUsername())) {
			AuthContext.addUser(authInfo);
		}

		AuthContext.setClientIp(ip);
	}


	private String refreshToken(String username, String password) throws Exception {
		AuthInfo authInfo = new AuthInfo(username, password);
		long tokenMinutes = tokenDurationMinutes + idleTimeoutMinutes;
		authInfo.setExpireTime(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(tokenMinutes));

		String json = JsonKit.toJson(authInfo);

		String token = AES.encrypt(json, authKey, authIv);
		logger.info("UserLoginAction#refreshToken new token: {}", token);

		// 将登录信息置于AuthContext
		AuthInfo userItem = AuthInfo.builder().username(username).token(token).tokenChangeFlag(true).build();
		AuthContext.addUser(userItem);

		return token;
	}


}
