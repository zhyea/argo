package com.zhyea.argo.constants;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 响应编码
 * <p>
 * 1xxxx api
 * 2xxxx mock
 * 3xxxx 权限
 * 4xxxx CMS
 *
 * @author robin
 */
public enum ResponseCode implements CodeDescEnum {


	/**
	 * 成功
	 */
	SUCCESS(0, "成功"),

	/**
	 * 暂停
	 */
	SUSPEND(99, "暂停"),


	/**
	 * 用户已登出
	 */
	USER_AUTH_ERROR(100, "用户已登出"),


	/**
	 * 未定义错误
	 */
	ERROR(1000, "未定义错误"),


	/**
	 * 参数错误
	 */
	ARGUMENT_ERROR(10000, "参数错误"),


	/**
	 * 常见错误
	 */
	RECORD_NOT_EXISTS_ERROR(10009, "记录不存在"),

	MODIFY_FIXED_INFO_ERROR(10010, "固定属性不允许修改"),

	/**
	 * 用户名或密码错误
	 */
	USER_LOGIN_INFO_ERROR(30000, "用户名或密码错误"),

	CONFIRM_PASSWORD_NOT_MATCH(30001, "确认密码与密码不一致"),


	/**
	 * CMS app相关问题
	 */
	APP_DUPLICATE_ERROR(40100, "应用已存在"),

	APP_ABSENT_ERROR(40101, "应用不存在"),


	/**
	 * CMS fcm相关问题
	 */
	FCM_DUPLICATE_ERROR(40200, "相同组件模型已存在"),

	DELETE_WITH_FCI_ERROR(40201, "该组件模型已被使用，无法删除"),


	/**
	 * CMS fci相关问题
	 */
	EDIT_EXPIRED_RECORD_ERROR(40300, "已过期记录不允许修改"),

	FCI_PROP_EFFECTIVE_TIME_OVERLAP(40301, "组件属性生效时间存在重叠"),

	FCI_PROP_EFFECTIVE_START_TIME_NOT_ALLOW_EDIT(40302, "组件属性生效时间开始时间不允许修改"),

	FCI_NOT_EXISTS_ERROR(40303, "组件实例不存在"),


	/**
	 * CMS appPage相关问题
	 */
	APP_PAGE_ALREADY_EXISTS_ERROR(40400, "相同ID页面已存在"),

	;


	public final int code;

	public final String msg;


	ResponseCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}


	@Override
	public String getDesc() {
		return msg;
	}


	@Override
	public int getCode() {
		return code;
	}
}
