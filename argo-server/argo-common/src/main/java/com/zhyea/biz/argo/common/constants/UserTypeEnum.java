package com.zhyea.biz.argo.common.constants;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * 用户类型
 *
 * @author robin
 */
public enum UserTypeEnum implements CodeDescEnum {


    /**
     * 超级管理员
     */
    SUPER_ADMIN(1, "超级管理员"),


    /**
     * 管理员
     */
    ADMIN(2, "管理员"),


    /**
     * 客户
     */
    CUSTOMER(3, "客户"),

    ;


    public final int code;


    public final String desc;


    UserTypeEnum(int code, String desc) {
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
