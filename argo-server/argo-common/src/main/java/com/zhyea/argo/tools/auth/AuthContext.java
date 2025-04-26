package com.zhyea.argo.tools.auth;

/**
 * 用户登录信息及权限信息上下文
 *
 * @author robin
 */
public final class AuthContext {


	/**
	 * user信息
	 */
	private static final ThreadLocal<AuthInfo> T_USER = new InheritableThreadLocal<>();


	/**
	 * 将用户信息保存到线程缓存
	 *
	 * @param user 用户信息
	 */
	public static void addUser(AuthInfo user) {
		T_USER.set(user);
	}


	/**
	 * 获取用户名
	 *
	 * @return 用户名
	 */
	public static String getUsername() {
		AuthInfo user = T_USER.get();
		return null == user ? null : user.getUsername();
	}


	/**
	 * 获取当前的token信息
	 *
	 * @return token信息
	 */
	public static String getToken() {
		AuthInfo user = T_USER.get();
		return null == user ? null : user.getToken();
	}


	/**
	 * 获取当前登录用户的ip
	 *
	 * @return ip
	 */
	public static String getClientIp() {
		AuthInfo user = T_USER.get();
		return null == user ? null : user.getIp();
	}


	/**
	 * 设置当前登录用户的ip
	 */
	public static void setClientIp(String ip) {
		AuthInfo user = T_USER.get();
		if (null != user) {
			user.setIp(ip);
		}
	}


	/**
	 * 清理当前线程的缓存
	 */
	public static void clear() {
		T_USER.remove();
	}


	/**
	 * 重置token变更标记
	 */
	public static void resetTokenChangeFlag() {
		AuthInfo user = T_USER.get();
		if (null != user) {
			user.setTokenChangeFlag(false);
		}
	}


	/**
	 * 判断token是否变更
	 *
	 * @return true:变更，false:未变更
	 */
	public static boolean isTokenChanged() {
		AuthInfo user = T_USER.get();
		return null != user && user.isTokenChangeFlag();
	}


	private AuthContext() {
		throw new UnsupportedOperationException("private constructor, cannot be accessed!");
	}
}
