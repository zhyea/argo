package com.zhyea.argo.cms.controller;


import com.zhyea.argo.cms.action.FciPropMaintainAction;
import com.zhyea.argo.cms.model.item.FciPropItem;
import com.zhyea.argo.cms.model.request.fci.*;
import com.zhyea.argo.cms.service.FciPropService;
import org.chobit.commons.model.response.PageResult;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组件属性相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/cms/fci/prop")
public class FciPropController {


	private final FciPropService fciPropService;

	private final FciPropMaintainAction fciPropMaintainAction;

	@Autowired
	public FciPropController(FciPropService fciPropService, FciPropMaintainAction fciPropMaintainAction) {
		this.fciPropService = fciPropService;
		this.fciPropMaintainAction = fciPropMaintainAction;
	}


	/**
	 * 新增组件属性
	 *
	 * @param request 新增组件属性请求
	 * @return 新增的组件属性id
	 */
	@PostMapping("/add")
	public Long add(@RequestBody @Validated FciPropAddRequest request) {
		return fciPropMaintainAction.add(request);
	}


	/**
	 * 修改组件属性
	 *
	 * @param request 修改组件属性请求
	 * @return 是否修改成功
	 */
	@PostMapping("/edit")
	public boolean edit(@RequestBody @Validated FciPropEditRequest request) {
		return fciPropMaintainAction.edit(request);
	}


	/**
	 * 根据ID获取组件属性
	 *
	 * @param request 请求
	 * @return 组件属性
	 */
	@PostMapping("/get")
	public FciPropItem get(@RequestBody @Validated FciPropIdRelateRequest request) {
		return fciPropService.get(request.getPropId());
	}


	/**
	 * 根据ID删除组件属性
	 *
	 * @param request 请求
	 * @return 是否删除成功
	 */
	@PostMapping("/delete")
	public boolean delete(@RequestBody @Validated FciPropIdRelateRequest request) {
		return fciPropService.delete(request.getPropId());
	}


	/**
	 * 根据组件ID获取组件属性列表
	 *
	 * @param request 请求
	 * @return 组件属性列表
	 */
	@PostMapping("/list")
	public PageResult<FciPropItem> list(@RequestBody @Validated FciPropQueryRequest request) {
		return fciPropService.findByFciId(request);
	}


	/**
	 * 根据组件ID获取组件属性列表
	 *
	 * @param request 请求
	 * @return 组件属性列表
	 */
	@PostMapping("/switch")
	public boolean switchProp(@RequestBody @Validated FciPropSwitchRequest request) {
		return fciPropService.switchProp(request.getPropId(), request.getSwitchFlag());
	}

}
