package com.zhyea.argo.cms.controller;


import com.zhyea.argo.cms.model.item.FciItem;
import com.zhyea.argo.cms.model.request.fci.*;
import com.zhyea.argo.cms.service.FciService;
import org.chobit.commons.model.response.PageResult;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 组件接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/cms/fci")
public class FciController {


	private final FciService fciService;

	@Autowired
	public FciController(FciService fciService) {
		this.fciService = fciService;
	}


	/**
	 * 新增组件实例
	 *
	 * @param request 新增组件实例请求
	 * @return 新增的组件实例id
	 */
	@RequestMapping("/add")
	public Long add(@RequestBody @Validated FciAddRequest request) {
		return fciService.add(request);
	}


	/**
	 * 修改组件实例
	 *
	 * @param request 修改组件实例请求
	 * @return 是否修改成功
	 */
	@PostMapping("/edit")
	public boolean edit(@RequestBody @Validated FciEditRequest request) {
		return fciService.edit(request);
	}


	/**
	 * 根据ID获取组件实例
	 *
	 * @param request 根据ID获取组件实例请求
	 * @return 组件实例
	 */
	@PostMapping("/get")
	public FciItem get(@RequestBody @Validated FciIdRelateRequest request) {
		return fciService.getById(request.getFciId());
	}


	/**
	 * 根据ID删除组件实例
	 *
	 * @param request 根据ID删除组件实例请求
	 * @return 是否删除成功
	 */
	@PostMapping("/delete")
	public boolean delete(@RequestBody @Validated FciIdRelateRequest request) {
		return fciService.deleteById(request.getFciId());
	}


	/**
	 * 分页查询组件实例
	 *
	 * @param request 分页查询组件实例请求
	 * @return 组件实例列表
	 */
	@PostMapping("/list")
	public PageResult<FciItem> findInPage(@RequestBody @Validated FciQueryRequest request) {
		return fciService.findInPage(request);
	}


	/**
	 * 根据页面编码查询组件实例
	 *
	 * @param request 根据页面编码查询组件实例请求
	 * @return 组件实例列表
	 */
	@PostMapping("/find-by-page")
	public List<FciItem> findByPageCode(@RequestBody @Validated FciPageCodeRequest request) {
		return fciService.findByPageCode(request.getPageCode());
	}

}
