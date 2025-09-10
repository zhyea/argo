package com.zhyea.argo.cms.action;

import com.zhyea.argo.cms.convert.FciConverter;
import com.zhyea.argo.cms.model.item.FciItem;
import com.zhyea.argo.cms.model.item.FcmItem;
import com.zhyea.argo.cms.model.request.fci.FciAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciEditRequest;
import com.zhyea.argo.cms.service.FciReqService;
import com.zhyea.argo.cms.service.FciService;
import com.zhyea.argo.cms.service.FcmService;
import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.constants.enums.DataBindFlagEnum;
import com.zhyea.argo.constants.enums.FciReqBindTypeEnum;
import com.zhyea.argo.data.entity.cms.FciEntity;
import com.zhyea.argo.except.ArgoServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 组件实例维护
 *
 * @author robin
 * @since 2025/9/10 7:51
 */
@Component
public class FciMaintainAction {


	private final FciService fciService;
	private final FciConverter fciConverter;
	private final FcmService fcmService;
	private final FciReqService fciReqService;


	@Autowired
	public FciMaintainAction(FciService fciService, FciConverter fciConverter, FcmService fcmService, FciReqService fciReqService) {
		this.fciService = fciService;
		this.fciConverter = fciConverter;
		this.fcmService = fcmService;
		this.fciReqService = fciReqService;
	}


	/**
	 * 新增组件实例
	 *
	 * @param request 新增组件实例请求
	 * @return 新增的组件实例id
	 */
	public Long add(FciAddRequest request) {
		FcmItem fcmItem = fcmService.getById(request.getFcmId());
		if (null == fcmItem) {
			throw new ArgoServerException(ResponseCode.FCM_NOT_EXISTS_ERROR);
		}

		FciEntity entity = fciConverter.addRequest2Entity(request);
		fciService.add(entity);

		if(DataBindFlagEnum.BIND_DATA.is(request.getDataBindFlag())){
			fciReqService.add(request, FciReqBindTypeEnum.FCI, entity.getId());
		}

		return entity.getId();
	}




	public boolean modify(FciEditRequest request) {
		FciItem his = fciService.getById(request.getId());
		if(null ==  his){
			throw new ArgoServerException(ResponseCode.FCI_NOT_EXISTS_ERROR);
		}

		if(DataBindFlagEnum.BIND_DATA.is(his.get())){

		}


		FciEntity entity = fciConverter.modifyRequest2Entity(request);

		return fciService.edit(entity);
	}


}
