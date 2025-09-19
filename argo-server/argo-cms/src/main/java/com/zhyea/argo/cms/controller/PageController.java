package com.zhyea.argo.cms.controller;

import com.zhyea.argo.cms.action.PageFciAssembleAction;
import com.zhyea.argo.model.item.PageFciAssembleItem;
import com.zhyea.argo.model.item.PageItem;
import com.zhyea.argo.model.request.fci.FciIdRelateRequest;
import com.zhyea.argo.cms.service.PageService;
import com.zhyea.argo.model.request.page.*;
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
	private final PageFciAssembleAction pageFciAssembleAction;


	@Autowired
	public PageController(PageService pageService, PageFciAssembleAction pageFciAssembleAction) {
		this.pageService = pageService;
		this.pageFciAssembleAction = pageFciAssembleAction;
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


	/**
	 * 挂链组件实例
	 *
	 * @param request 关联请求
	 * @return 是否成功
	 */
	@PostMapping("/map-fci")
	public boolean mapFci(@RequestBody @Validated PageFciMapRequest request) {
		return pageService.maintainFci(request);
	}


	/**
	 * 获取页面组件实例列表
	 *
	 * @param request 页面id相关请求
	 * @return 页面相关组件实例列表
	 */
	@PostMapping("/relate-fci-list")
	public PageFciAssembleItem pageFciList(@RequestBody @Validated PageIdRelateRequest request) {
		return pageFciAssembleAction.pageFciList(request.getPageId());
	}


	/**
	 * 获取使用组件实例的页面列表
	 *
	 * @param request 组件id相关请求
	 * @return 使用组件的页面列表
	 */
	@PostMapping("/fci-usage")
	public List<PageItem> findFciUsage(@RequestBody @Validated FciIdRelateRequest request) {
		return pageService.findFciUsage(request.getFciId());
	}
}
