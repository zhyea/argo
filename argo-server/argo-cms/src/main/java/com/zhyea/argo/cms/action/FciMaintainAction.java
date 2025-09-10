package com.zhyea.argo.cms.action;

import com.zhyea.argo.cms.convert.FciConverter;
import com.zhyea.argo.cms.model.item.FciItem;
import com.zhyea.argo.cms.model.item.FciPropItem;
import com.zhyea.argo.cms.model.item.FcmItem;
import com.zhyea.argo.cms.model.request.fci.FciAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciEditRequest;
import com.zhyea.argo.cms.service.FciPropService;
import com.zhyea.argo.cms.service.FciReqService;
import com.zhyea.argo.cms.service.FciService;
import com.zhyea.argo.cms.service.FcmService;
import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.constants.enums.DataBindFlagEnum;
import com.zhyea.argo.constants.enums.FciReqBindTypeEnum;
import com.zhyea.argo.data.entity.cms.FciEntity;
import com.zhyea.argo.except.ArgoServerException;
import org.chobit.commons.utils.Collections2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
	private final FciPropService fciPropService;


	@Autowired
	public FciMaintainAction(FciService fciService,
	                         FciConverter fciConverter,
	                         FcmService fcmService,
	                         FciReqService fciReqService,
	                         FciPropService fciPropService) {
		this.fciService = fciService;
		this.fciConverter = fciConverter;
		this.fcmService = fcmService;
		this.fciReqService = fciReqService;
		this.fciPropService = fciPropService;
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

		if (DataBindFlagEnum.BIND_DATA.is(request.getDataBindFlag())) {
			fciReqService.add(request, FciReqBindTypeEnum.FCI, entity.getId());
		}

		return entity.getId();
	}


	/**
	 * 修改组件实例
	 *
	 * @param request 修改组件实例请求
	 * @return 修改成功
	 */
	public boolean modify(FciEditRequest request) {
		FciItem his = fciService.getById(request.getId());
		if (null == his) {
			throw new ArgoServerException(ResponseCode.FCI_NOT_EXISTS_ERROR);
		}

		List<FciPropItem> props = fciPropService.findValidByFciId(request.getId());
		List<FciPropItem> inheritedProps = props.stream().filter(prop -> DataBindFlagEnum.INHERIT.is(prop.getDataBindFlag())).toList();

		if (DataBindFlagEnum.BIND_DATA.is(his.getDataBindFlag()) && DataBindFlagEnum.BIND_DATA.isNot(request.getDataBindFlag())) {
			if (Collections2.isNotEmpty(inheritedProps)) {
				throw new ArgoServerException(ResponseCode.EXISTS_INHERITED_FCI_PROPS);
			}
			// 删除数据绑定请求
			fciReqService.deleteByBelongId(FciReqBindTypeEnum.FCI, request.getId());
		}
		if (DataBindFlagEnum.BIND_DATA.isNot(his.getDataBindFlag()) && DataBindFlagEnum.BIND_DATA.is(request.getDataBindFlag())) {
			if (Collections2.isNotEmpty(inheritedProps)) {
				throw new ArgoServerException(ResponseCode.EXISTS_INHERITED_FCI_PROPS);
			}
			// 新增数据绑定请求
			fciReqService.add(request, FciReqBindTypeEnum.FCI, request.getId());
		}
		if (DataBindFlagEnum.BIND_DATA.is(his.getDataBindFlag()) && DataBindFlagEnum.BIND_DATA.is(request.getDataBindFlag())) {
			// 修改数据绑定请求
			fciReqService.modify(request, FciReqBindTypeEnum.FCI, request.getId());
		}

		return fciService.modify(request);
	}


}
