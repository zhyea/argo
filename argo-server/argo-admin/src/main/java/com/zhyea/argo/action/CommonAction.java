package com.zhyea.argo.action;

import com.zhyea.argo.constants.enums.FcScopeEnum;
import com.zhyea.argo.constants.enums.FcmTypeEnum;
import com.zhyea.argo.constants.enums.YesOrNo;
import org.chobit.commons.utils.EnumKit;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用业务处理类
 *
 * @author robin
 */
@Component
public class CommonAction {


    /**
     * 将枚举转换为Map结构，便于前端使用
     *
     * @return 枚举对应的Map结构
     */
    public Map<String, Map<Integer, String>> enumMap() {
        Map<String, Map<Integer, String>> enumMap = new HashMap<>(16);

        enumMap.put("FcmTypeEnum", EnumKit.mapOf(FcmTypeEnum.class));
        enumMap.put("FcScopeEnum", EnumKit.mapOf(FcScopeEnum.class));
        enumMap.put("YesOrNo", EnumKit.mapOf(YesOrNo.class));

        return enumMap;
    }


}
