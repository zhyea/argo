package com.zhyea.argo.api.controller;

import com.zhyea.argo.api.action.FciDetailQueryAction;
import com.zhyea.argo.api.model.item.FciDetailItem;
import com.zhyea.argo.api.model.request.FciDetailRequest;
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
@RequestMapping("/fci")
public class FciApiController {


	private final FciDetailQueryAction pageQueryAction;

	@Autowired
	public FciApiController(FciDetailQueryAction pageQueryAction) {
		this.pageQueryAction = pageQueryAction;
	}


	@PostMapping("/detail")
	public FciDetailItem queryPageDetail(@RequestBody @Validated FciDetailRequest request,
	                                     HttpServletRequest httpReq) {
		return pageQueryAction.queryFciDetail(request.getFciCode(), httpReq);
	}


}
