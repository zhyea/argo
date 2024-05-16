package com.zhyea.biz.argo.tools;

import org.springframework.context.ApplicationContext;

/**
 * Spring Bean 获取工具包
 * <p>
 * 主要用于需要使用静态方式获取Bean的场景
 *
 * @author robin
 */
public final class BeanKit {


    /**
     * 将管理上下文的applicationContext设置成静态变量，供全局调用
     */
    private static ApplicationContext context;


    public static void init(ApplicationContext context) {
        if (null == BeanKit.context) {
            BeanKit.context = context;
        }
    }


    public static <T> T get(Class<T> c) {
        return context.getBean(c);
    }


    private BeanKit() {
    }

}
