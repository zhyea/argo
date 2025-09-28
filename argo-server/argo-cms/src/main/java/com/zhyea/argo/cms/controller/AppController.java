package com.zhyea.argo.cms.controller;

import com.zhyea.argo.biz.service.AppService;
import com.zhyea.argo.cms.action.AppMaintainAction;
import com.zhyea.argo.model.item.AppItem;
import com.zhyea.argo.model.request.app.*;
import org.chobit.commons.model.response.PageResult;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/cms/app")
public class AppController {


	private final AppService appService;
	private final AppMaintainAction appMaintainAction;

	@Autowired
	public AppController(AppService appService, AppMaintainAction appMaintainAction) {
		this.appService = appService;
		this.appMaintainAction = appMaintainAction;
	}

	/**
	 * 新增应用记录
	 *
	 * @param request 应用信息
	 * @return 应用id
	 */
	@PostMapping("/add")
	public Long add(@RequestBody @Validated AppAddRequest request) {
		return appService.add(request);
	}


	/**
	 * 修改应用记录
	 *
	 * @param request 应用信息
	 * @return 影响行数
	 */
	@PostMapping("/edit")
	public boolean edit(@RequestBody @Validated AppEditRequest request) {
		return appService.edit(request);
	}


	/**
	 * 根据id获取应用信息
	 *
	 * @param request 应用id相关请求
	 * @return 应用信息
	 */
	@PostMapping("/get")
	public AppItem getById(@RequestBody @Validated AppIdRelateRequest request) {
		return appService.get(request.getAppId());
	}


	/**
	 * 根据id删除应用信息
	 *
	 * @param request 请求信息
	 * @return 是否删除成功
	 */
	@PostMapping("/delete")
	public boolean deleteById(@RequestBody @Validated AppIdRelateRequest request) {
		return appMaintainAction.delete(request.getAppId());
	}


	/**
	 * 获取所有应用信息
	 *
	 * @return 应用记录
	 */
	@GetMapping("/all")
	public List<AppItem> findAll() {
		return appService.findAll();
	}


	/**
	 * 分页查询应用信息
	 *
	 * @param request 查询请求
	 * @return 应用记录
	 */
	@PostMapping("/list")
	public PageResult<AppItem> findInPage(@RequestBody AppPageRequest request) {
		return appService.findInPage(request);
	}


	/**
	 * 查询应用信息
	 *
	 * @param request 查询请求
	 * @return 应用记录
	 */
	@PostMapping("/query")
	public List<AppItem> query(@RequestBody AppQueryRequest request) {
		return appService.query(request);
	}


	/**
	 * 生成应用编码
	 *
	 * @return 应用编码
	 */
	@RequestMapping("/app-code")
	public String generateAppCode() {
		return appService.generateAppCode();
	}

}
