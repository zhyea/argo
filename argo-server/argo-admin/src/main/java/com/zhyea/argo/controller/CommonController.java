package com.zhyea.argo.controller;


import com.zhyea.argo.action.CommonAction;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 公共接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/common")
public class CommonController {


    private final CommonAction commonAction;

    @Autowired
    public CommonController(CommonAction commonAction) {
        this.commonAction = commonAction;
    }


    /**
     * 枚举转换为Map结构，便于前端使用
     *
     * @return 枚举对应的Map结构
     */
    @RequestMapping("/enums")
    public Map<String, Map<Integer, String>> enums() {
        return commonAction.enumMap();
    }

}
