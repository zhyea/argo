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
     * 用户名或密码错误
     */
    USER_LOGIN_INFO_ERROR(30000, "用户名或密码错误"),

    CONFIRM_PASSWORD_NOT_MATCH(30001, "确认密码与密码不一致"),


    /**
     * CMS app相关问题
     */
    APP_DUPLICATE_ERROR(40100, "应用已存在"),


    /**
     * CMS fcm相关问题
     */
    FCM_DUPLICATE_ERROR(40200, "相同组件模型已存在"),

    DELETE_WITH_FCI_ERROR(40201, "该组件模型已被使用，无法删除")


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
