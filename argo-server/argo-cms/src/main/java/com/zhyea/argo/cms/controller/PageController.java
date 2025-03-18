package com.zhyea.argo.cms.controller;

import com.zhyea.argo.cms.model.item.PageItem;
import com.zhyea.argo.cms.model.request.page.*;
import com.zhyea.argo.cms.service.PageService;
import org.chobit.commons.model.response.PageResult;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用页面相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/cms/app/page")
public class PageController {


	private final PageService pageService;


	@Autowired
	public PageController(PageService pageService) {
		this.pageService = pageService;
	}


	/**
	 * 新增应用页面
	 *
	 * @param request 新增应用页面请求
	 * @return 应用页面记录id
	 */
	@PostMapping("/add")
	public Long add(@RequestBody @Validated PageAddRequest request) {
		return pageService.add(request);
	}


	/**
	 * 修改应用页面
	 *
	 * @param request 修改应用页面请求
	 * @return 是否修改成功
	 */
	@PostMapping("/edit")
	public boolean modify(@RequestBody @Validated PageEditRequest request) {
		return pageService.modify(request);
	}


	/**
	 * 根据id获取应用页面
	 *
	 * @param request 应用页面id相关请求
	 * @return 应用页面信息
	 */
	@PostMapping("/get")
	public PageItem get(@RequestBody @Validated PageIdRelateRequest request) {
		return pageService.get(request.getPageId());
	}


	/**
	 * 根据id删除应用页面
	 *
	 * @param request 应用页面id相关请求
	 * @return 是否删除成功
	 */
	@PostMapping("/delete")
	public boolean delete(@RequestBody @Validated PageIdRelateRequest request) {
		return pageService.delete(request.getPageId());
	}


	/**
	 * 根据应用id获取应用页面列表
	 *
	 * @param request 应用id相关请求
	 * @return 应用页面列表
	 */
	@PostMapping("/list")
	public PageResult<PageItem> findByAppId(@RequestBody @Validated PageQueryRequest request) {
		return pageService.findByAppId(request);
	}


	/**
	 * 查询应用下的全部页面
	 *
	 * @param request 查询请求
	 * @return 应用页面列表
	 */
	@PostMapping("/query")
	public List<PageItem> queryAppPages(@RequestBody @Validated PageListQueryRequest request) {
		return pageService.queryAppPages(request);
	}


	/**
	 * 生成页面code
	 *
	 * @return 页面code
	 */
	@GetMapping("/page-code")
	public String pageCode() {
		return pageService.generatePageCode();
	}
}
