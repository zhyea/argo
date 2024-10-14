package com.zhyea.argo.cms.controller;


import com.zhyea.argo.cms.model.item.FciPropItem;
import com.zhyea.argo.cms.model.request.fci.FciIdRelateRequest;
import com.zhyea.argo.cms.model.request.fci.FciPropAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciPropEditRequest;
import com.zhyea.argo.cms.model.request.fci.FciPropIdRelateRequest;
import com.zhyea.argo.cms.service.FciPropService;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 组件属性相关接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/fci/prop")
public class FciPropController {


	private final FciPropService fciPropService;

	@Autowired
	public FciPropController(FciPropService fciPropService) {
		this.fciPropService = fciPropService;
	}


	/**
	 * 新增组件属性
	 *
	 * @param request 新增组件属性请求
	 * @return 新增的组件属性id
	 */
	@PostMapping("/add")
	public Long add(@RequestBody @Valid FciPropAddRequest request) {
		return fciPropService.add(request);
	}


	/**
	 * 修改组件属性
	 *
	 * @param request 修改组件属性请求
	 * @return 是否修改成功
	 */
	@PostMapping("/edit")
	public boolean edit(@RequestBody @Valid FciPropEditRequest request) {
		return fciPropService.edit(request);
	}


	/**
	 * 根据ID获取组件属性
	 *
	 * @param request 请求
	 * @return 组件属性
	 */
	@PostMapping("/get")
	public FciPropItem get(@RequestBody @Valid FciPropIdRelateRequest request) {
		return fciPropService.get(request.getPropId());
	}


	/**
	 * 根据ID删除组件属性
	 *
	 * @param request 请求
	 * @return 是否删除成功
	 */
	@PostMapping("/delete")
	public boolean delete(@RequestBody @Valid FciPropIdRelateRequest request) {
		return fciPropService.delete(request.getPropId());
	}


	/**
	 * 根据组件ID获取组件属性列表
	 *
	 * @param request 请求
	 * @return 组件属性列表
	 */
	@PostMapping("/list")
	public List<FciPropItem> list(@RequestBody @Valid FciIdRelateRequest request) {
		return fciPropService.findByFciId(request.getFciId());
	}

}
