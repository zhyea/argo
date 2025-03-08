package com.zhyea.argo.cms.controller;

import com.zhyea.argo.cms.model.item.AppPageItem;
import com.zhyea.argo.cms.model.request.app.AppPageAddRequest;
import com.zhyea.argo.cms.model.request.app.AppPageEditRequest;
import com.zhyea.argo.cms.model.request.app.AppPageIdRelateRequest;
import com.zhyea.argo.cms.model.request.app.AppPageQueryRequest;
import com.zhyea.argo.cms.service.AppPageService;
import org.chobit.commons.model.response.PageResult;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 应用页面相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/cms/app/page")
public class AppPageController {


	private final AppPageService appPageService;


	@Autowired
	public AppPageController(AppPageService appPageService) {
		this.appPageService = appPageService;
	}


	/**
	 * 新增应用页面
	 *
	 * @param request 新增应用页面请求
	 * @return 应用页面记录id
	 */
	@PostMapping("/add")
	public Long add(@RequestBody @Validated AppPageAddRequest request) {
		return appPageService.add(request);
	}


	/**
	 * 修改应用页面
	 *
	 * @param request 修改应用页面请求
	 * @return 是否修改成功
	 */
	@PostMapping("/edit")
	public boolean modify(@RequestBody @Validated AppPageEditRequest request) {
		return appPageService.modify(request);
	}


	/**
	 * 根据id获取应用页面
	 *
	 * @param request 应用页面id相关请求
	 * @return 应用页面信息
	 */
	@PostMapping("/get")
	public AppPageItem get(@RequestBody @Validated AppPageIdRelateRequest request) {
		return appPageService.get(request.getPageId());
	}


	/**
	 * 根据id删除应用页面
	 *
	 * @param request 应用页面id相关请求
	 * @return 是否删除成功
	 */
	@PostMapping("/delete")
	public boolean delete(@RequestBody @Validated AppPageIdRelateRequest request) {
		return appPageService.delete(request.getPageId());
	}


	/**
	 * 根据应用id获取应用页面列表
	 *
	 * @param request 应用id相关请求
	 * @return 应用页面列表
	 */
	@PostMapping("/list")
	public PageResult<AppPageItem> findByAppId(@RequestBody @Validated AppPageQueryRequest request) {
		return appPageService.findByAppId(request);
	}


	/**
	 * 生成页面code
	 *
	 * @return 页面code
	 */
	@GetMapping("/page-code")
	public String pageCode() {
		return appPageService.generatePageCode();
	}
}
