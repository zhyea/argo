package com.zhyea.argo.service;

import com.zhyea.argo.constants.enums.UserOptionKeyEnum;
import com.zhyea.argo.data.mapper.adm.UserOptionsMapper;
import com.zhyea.argo.tools.auth.AuthContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.chobit.commons.utils.Convert.toLong;

/**
 * 用户选项业务处理
 *
 * @author robin
 * @since 2025/6/24 8:14
 */
@Service
public class UserOptionsService {


	private final UserOptionsMapper userOptionsMapper;

	@Autowired
	public UserOptionsService(UserOptionsMapper userOptionsMapper) {
		this.userOptionsMapper = userOptionsMapper;
	}


	/**
	 * 设置用户最后访问的app
	 *
	 * @param appId appId
	 */
	public boolean setLastVisitApp(Long appId) {
		String username = AuthContext.getUsername();
		this.set(username, UserOptionKeyEnum.LAST_VISIT_APP, String.valueOf(appId));
		return true;
	}


	/**
	 * 获取用户最后访问的app
	 *
	 * @return appId
	 */
	public Long getLastVisitApp() {
		String username = AuthContext.getUsername();
		String appId = this.get(username, UserOptionKeyEnum.LAST_VISIT_APP);
		return appId == null ? null : toLong(appId);
	}


	private void set(String username, UserOptionKeyEnum optionKey, String optionVal) {
		userOptionsMapper.set(username, optionKey.key, optionVal);
	}


	private String get(String username, UserOptionKeyEnum optionKey) {
		return userOptionsMapper.get(username, optionKey.key);
	}

}
