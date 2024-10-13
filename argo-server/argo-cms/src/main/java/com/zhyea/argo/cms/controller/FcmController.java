package com.zhyea.argo.cms.controller;

import com.zhyea.argo.cms.service.FcmService;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组件模型相关接口
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/fcm")
public class FcmController {



	private final FcmService fcmService;


	@Autowired
	public FcmController(FcmService fcmService) {
		this.fcmService = fcmService;
	}



}
