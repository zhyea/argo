package com.zhyea.argo.cms.controller;

import com.zhyea.argo.model.item.FcmItem;
import com.zhyea.argo.model.request.fcm.FcmAddRequest;
import com.zhyea.argo.model.request.fcm.FcmEditRequest;
import com.zhyea.argo.model.request.fcm.FcmIdRelateRequest;
import com.zhyea.argo.model.request.fcm.FcmQueryRequest;
import com.zhyea.argo.biz.service.FcmService;
import org.chobit.commons.model.response.PageResult;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组件模型相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/cms/fcm")
public class FcmController {


	private final FcmService fcmService;


	@Autowired
	public FcmController(FcmService fcmService) {
		this.fcmService = fcmService;
	}


	/**
	 * 新增组件模型
	 *
	 * @param request 新增组件模型请求
	 * @return 新增的组件模型id
	 */
	@PostMapping("/add")
	public Long add(@RequestBody @Validated FcmAddRequest request) {
		return fcmService.add(request);
	}


	/**
	 * 修改组件模型
	 *
	 * @param request 修改组件模型请求
	 * @return 是否修改成功
	 */
	@PostMapping("/edit")
	public boolean edit(@RequestBody @Validated FcmEditRequest request) {
		return fcmService.modify(request);
	}


	/**
	 * 根据id获取组件模型
	 *
	 * @param request 获取组件模型请求
	 * @return 组件模型
	 */
	@PostMapping("/get")
	public FcmItem getFcmItem(@RequestBody @Validated FcmIdRelateRequest request) {
		return fcmService.getById(request.getFcmId());
	}


	/**
	 * 根据id删除组件模型
	 *
	 * @param request 删除组件模型请求
	 * @return 是否删除成功
	 */
	@PostMapping("/delete")
	public boolean delete(@RequestBody @Validated FcmIdRelateRequest request) {
		return fcmService.deleteById(request.getFcmId());
	}


	/**
	 * 根据条件查询组件模型列表
	 *
	 * @param request 查询组件模型请求
	 * @return 组件模型列表
	 */
	@PostMapping("/list")
	public PageResult<FcmItem> findByPage(@RequestBody @Validated FcmQueryRequest request) {
		return fcmService.queryInPage(request);
	}
}
