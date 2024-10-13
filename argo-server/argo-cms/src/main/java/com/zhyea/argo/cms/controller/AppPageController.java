package com.zhyea.argo.cms.controller;

import com.zhyea.argo.cms.model.item.AppPageItem;
import com.zhyea.argo.cms.model.request.app.*;
import com.zhyea.argo.cms.service.AppPageService;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 应用页面相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/app/page")
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
	public Long add(@RequestBody @Valid AppPageAddRequest request) {
		return appPageService.add(request);
	}


	/**
	 * 修改应用页面
	 *
	 * @param request 修改应用页面请求
	 * @return 是否修改成功
	 */
	@PostMapping("/edit")
	public boolean modify(@RequestBody @Valid AppPageEditRequest request) {
		return appPageService.modify(request);
	}


	/**
	 * 根据id获取应用页面
	 *
	 * @param request 应用页面id相关请求
	 * @return 应用页面信息
	 */
	@PostMapping("/get")
	public AppPageItem get(@RequestBody @Valid AppPageIdRelateRequest request) {
		return appPageService.get(request.getPageId());
	}


	/**
	 * 根据id删除应用页面
	 *
	 * @param request 应用页面id相关请求
	 * @return 是否删除成功
	 */
	@PostMapping("/delete")
	public boolean delete(@RequestBody @Valid AppPageIdRelateRequest request) {
		return appPageService.delete(request.getPageId());
	}


	/**
	 * 根据应用id获取应用页面列表
	 *
	 * @param request 应用id相关请求
	 * @return 应用页面列表
	 */
	@PostMapping("/list")
	public List<AppPageItem> findByAppId(@RequestBody @Valid AppPageQueryRequest request) {
		return appPageService.findByAppId(request);
	}
}
