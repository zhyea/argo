package com.zhyea.argo.cms.action;

import com.zhyea.argo.biz.service.FciPropService;
import com.zhyea.argo.biz.service.FciService;
import com.zhyea.argo.except.ArgoServerException;
import com.zhyea.argo.model.item.FciItem;
import com.zhyea.argo.model.item.FciPropItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.zhyea.argo.constants.ResponseCode.FCI_NOT_EXISTS_ERROR;
import static com.zhyea.argo.constants.ResponseCode.FCI_PROP_NOT_EXISTS_ERROR;

/**
 * FCI属性查询
 *
 * @author robin
 * @since 2025/9/20 22:51
 */
@Slf4j
@Component
public class FciPropQueryAction {


	private final FciService fciService;
	private final FciPropService fciPropService;

	@Autowired
	public FciPropQueryAction(FciService fciService, FciPropService fciPropService) {
		this.fciService = fciService;
		this.fciPropService = fciPropService;
	}


	public FciPropItem getById(Long id) {
		FciPropItem item = fciPropService.get(id);
		if (null == item) {
			throw new ArgoServerException(FCI_PROP_NOT_EXISTS_ERROR);
		}

		FciItem fciItem = fciService.getById(item.getFciId());
		if (null == fciItem) {
			throw new ArgoServerException(FCI_NOT_EXISTS_ERROR);
		}

		item.setFciDataBindFlag(fciItem.getDataBindFlag());

		return item;
	}

}
