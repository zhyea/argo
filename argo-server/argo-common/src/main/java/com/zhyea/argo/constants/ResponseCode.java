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
	INVALID_TOKEN_ERROR(101, "token异常"),
	EXPIRED_TOKEN_ERROR(102, "token已过期，请重新登录"),
	CLIENT_IP_CHANGED_ERROR(103, "检测到用户IP地址变化，请重新登录"),


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
	EDIT_EXPIRED_RECORD_ERROR(10011, "已过期记录不允许修改"),


	/**
	 * 不需要明确分类的错误
	 */
	ILLEGAL_HTTP_METHOD_ERROR(20021, "非法的数据请求method"),


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
	FCM_NOT_EXISTS_ERROR(40202, "对应组件模型不存在"),


	/**
	 * CMS fci相关问题
	 */
	FCI_PROP_KEY_NOT_ALLOWED_EDIT(40300, "属性key不允许修改"),
	FCI_PROP_EFFECTIVE_TIME_OVERLAP(40301, "组件属性生效时间存在重叠"),
	FCI_PROP_EFFECTIVE_START_TIME_NOT_ALLOW_EDIT(40302, "组件属性生效时间开始时间不允许修改"),
	FCI_PROP_EFFECTIVE_TIME_IS_EMPTY(40303, "组件属性生效时间错误"),
	FCI_PROP_EFFECTIVE_START_TIME_AFTER_NOW(40304, "组件属性生效开始时间不能小于当前时间"),
	FCI_PROP_EFFECTIVE_END_TIME_AFTER_START(40305, "组件属性生效结束时间需要大于开始时间"),

	//DATA_BIND_URL_IS_EMPTY(40306, "数据链接或者值选择器不能为空"),

	FCI_PROP_EFFECTIVE_DATA_NOT_ALLOW_EDIT(40307, "生效中的配置项不允许修改"),
	EXPIRED_FCI_PROP_CANNOT_BE_DELETED(40308, "已过期的配置项不允许删除"),
	EFFECTIVE_FCI_PROP_CANNOT_BE_DELETED(40309, "生效中的配置项不允许删除"),
	FCI_NOT_EXISTS_ERROR(40310, "组件实例不存在"),
	FCI_EFFECTIVE_TIME_IS_EMPTY(40311, "组件实例生效时间错误"),
	FCI_EFFECTIVE_START_TIME_AFTER_NOW(40312, "组件实例生效开始时间不能小于当前时间"),
	FCI_EFFECTIVE_END_TIME_AFTER_START(40313, "组件实例生效结束时间需要大于开始时间"),

	PROP_DATA_BIND_URL_IS_BLANK(40360, "数据链接或者值选择器不能为空"),
	PROP_DATA_REQUEST_METHOD_IS_NULL(40361, "数据请求方式不能为空"),
	PROP_DATA_REQUEST_PARAMS_IS_BLANK(40362, "数据请求参数不能为空"),
	PROP_DATA_REQUEST_HEADERS_IS_BLANK(40363, "数据请求header不能为空"),
	PROP_DATA_VALUE_SELECTOR_IS_BLANK(40364, "数据值选择器不能为空"),

	EXISTS_INHERITED_FCI_PROPS(40365, "存在继承组件实例数据绑定关系的属性"),


	/**
	 * CMS appPage相关问题
	 */
	APP_PAGE_ALREADY_EXISTS_ERROR(40400, "相同ID页面已存在"),
	PAGE_NOT_EXISTS_ERROR(40401, "页面不存在"),
	PAGE_CANNOT_BE_DELETED_WITH_FCI(40402, "页面已关联组件，请先解除关联关系"),



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
