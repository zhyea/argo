package com.zhyea.argo.constants.enums;

import org.chobit.commons.contract.CodeDescEnum;

import java.time.LocalDateTime;

import static org.chobit.commons.constans.Symbol.EMPTY;

/**
 * 时间相关状态信息
 *
 * @author robin
 */
public enum TimeRelateStatusEnum implements CodeDescEnum {


    /**
     * 已过期
     */
    EXPIRED(1, "已过期"),


    /**
     * 生效中
     */
    IN_EFFECT(2, "生效中"),


    /**
     * 待生效
     */
    PENDING(3, "待生效"),


    ;


    public final int code;

    public final String desc;


    TimeRelateStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    @Override
    public int getCode() {
        return this.code;
    }


    @Override
    public String getDesc() {
        return this.desc;
    }


    /**
     * 分析时间相关状态
     *
     * @param periodType 周期类型
     * @param startTime  生效开始时间
     * @param endTime    生效结束时间
     * @return 时间相关状态
     */
    public static TimeRelateStatusEnum analyzeStatus(Integer periodType, LocalDateTime startTime, LocalDateTime endTime) {
        if (EffectivePeriodTypeEnum.LONG_TERM.is(periodType)) {
            return IN_EFFECT;
        }

        LocalDateTime now = LocalDateTime.now();

        if (EffectivePeriodTypeEnum.FIXED_TERM.is(periodType)) {
            if (now.isAfter(endTime)) {
                return EXPIRED;
            }

            if (now.isAfter(startTime) && (now.isBefore(endTime) || now.equals(endTime))) {
                return IN_EFFECT;
            }

            if (now.isBefore(startTime) || now.equals(startTime)) {
                return PENDING;
            }
        }

        return null;
    }


    /**
     * 分析时间相关状态描述
     *
     * @param periodType 周期类型
     * @param startTime  生效开始时间
     * @param endTime    生效结束时间
     * @return 时间相关状态描述
     */
    public static String analyzeStatusDesc(Integer periodType, LocalDateTime startTime, LocalDateTime endTime) {
        TimeRelateStatusEnum status = analyzeStatus(periodType, startTime, endTime);
        if (null == status) {
            return EMPTY;
        }

        return status.getDesc();
    }
}
