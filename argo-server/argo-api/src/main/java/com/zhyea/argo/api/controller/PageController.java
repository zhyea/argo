package com.zhyea.argo.api.controller;

import com.zhyea.argo.api.action.PageDetailQueryAction;
import com.zhyea.argo.api.model.item.PageDetailItem;
import com.zhyea.argo.api.model.request.PageDetailRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 页面API接口
 *
 * @author robin
 */
@ResponseWrapper
@RestController
@RequestMapping("/page")
public class PageController {


	private final PageDetailQueryAction pageQueryAction;

	@Autowired
	public PageController(PageDetailQueryAction pageQueryAction) {
		this.pageQueryAction = pageQueryAction;
	}


	@PostMapping("/detail")
	public PageDetailItem queryPageDetail(@RequestBody @Validated PageDetailRequest request, HttpServletRequest httpReq) {
		return pageQueryAction.query(request, httpReq);
	}


	@RequestMapping("/ping")
	public String ping() {
		return "Medea";
	}


}
