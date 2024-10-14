package com.zhyea.argo.constants.enums;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 用户状态
 *
 * @author robin
 */
public enum UserStateEnum implements CodeDescEnum {


    /**
     * 正常
     */
    NORMAL(1, "正常"),


    /**
     * 已过期
     */
    EXPIRED(2, "已过期"),


    /**
     * 暂时停用
     */
    SUSPEND(2, "暂时停用"),


    /**
     * 已禁用
     */
    FORBID(3, "已禁用"),
    ;


    public final int code;


    public final String desc;


    UserStateEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
