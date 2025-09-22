package com.zhyea.argo.cms.action;

import com.zhyea.argo.biz.convert.FciConverter;
import com.zhyea.argo.biz.service.*;
import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.constants.enums.DataBindFlagEnum;
import com.zhyea.argo.constants.enums.FciReqBindTypeEnum;
import com.zhyea.argo.data.entity.cms.FciEntity;
import com.zhyea.argo.except.ArgoServerException;
import com.zhyea.argo.model.item.FciItem;
import com.zhyea.argo.model.item.FciPropItem;
import com.zhyea.argo.model.item.FcmItem;
import com.zhyea.argo.model.item.PageItem;
import com.zhyea.argo.model.request.fci.FciAddRequest;
import com.zhyea.argo.model.request.fci.FciEditRequest;
import com.zhyea.argo.tools.Args;
import com.zhyea.argo.tools.ParallelKit;
import org.chobit.commons.utils.Collections2;
import org.chobit.commons.utils.ObjKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	private final PageService pageService;


	@Autowired
	public FciMaintainAction(FciService fciService,
	                         FciConverter fciConverter,
	                         FcmService fcmService,
	                         FciReqService fciReqService,
	                         FciPropService fciPropService, PageService pageService) {
		this.fciService = fciService;
		this.fciConverter = fciConverter;
		this.fcmService = fcmService;
		this.fciReqService = fciReqService;
		this.fciPropService = fciPropService;
		this.pageService = pageService;
	}


	/**
	 * 新增组件实例
	 *
	 * @param request 新增组件实例请求
	 * @return 新增的组件实例id
	 */
	@Transactional
	public Long add(FciAddRequest request) {
		FcmItem fcmItem = fcmService.getById(request.getFcmId());
		if (null == fcmItem) {
			throw new ArgoServerException(ResponseCode.FCM_NOT_EXISTS_ERROR);
		}

		FciEntity entity = fciConverter.addRequest2Entity(request);
		Long fciId = fciService.add(entity);

		if (DataBindFlagEnum.BIND_DATA.is(request.getDataBindFlag())) {
			fciReqService.add(request, FciReqBindTypeEnum.FCI, fciId);
		}

		return entity.getId();
	}


	/**
	 * 修改组件实例
	 *
	 * @param request 修改组件实例请求
	 * @return 修改成功
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean modify(FciEditRequest request) {
		Long fciId = request.getId();

		List<Object> list = ParallelKit.executeIgnoreErrors(() -> fciService.getById(fciId),
				() -> pageService.findFciUsage(fciId),
				() -> fciPropService.findEffectivePropsByFciId(fciId));

		if (Collections2.isEmpty(list) && null == list.getFirst()) {
			throw new ArgoServerException(ResponseCode.FCI_NOT_EXISTS_ERROR);
		}

		FciItem his = (FciItem) list.getFirst();
		if (null == his) {
			throw new ArgoServerException(ResponseCode.FCI_NOT_EXISTS_ERROR);
		}

		boolean isInUse = false;
		if (list.size() > 1 && null != list.get(1)) {
			List<PageItem> pageItems = (List<PageItem>) list.get(1);
			isInUse = Collections2.isNotEmpty(pageItems);
		}

		boolean existsInheritedProps = false;
		List<FciPropItem> inheritedProps = null;
		if (list.size() > 2 && null != list.get(2)) {
			List<FciPropItem> props = (List<FciPropItem>) list.get(2);
			inheritedProps = props.stream().filter(prop -> DataBindFlagEnum.INHERIT.is(prop.getDataBindFlag())).toList();
			existsInheritedProps = Collections2.isNotEmpty(inheritedProps);
		}

		if (isInUse) {
			// 组件被引用到页面后不允许再修改适用范围
			Args.checkEquals(request.getUsageScope(), his.getUsageScope(), ResponseCode.USAGE_SCOPE_CANNOT_BE_MODIFIED_WHEN_USED);
		}

		if (existsInheritedProps) {
			// 数据绑定信息不允许被修改
			boolean isDataBindInfoChanged = (ObjKit.nonEquals(request.getDataBindFlag(), his.getDataBindFlag())
					|| ObjKit.nonEquals(request.getDataUrl(), his.getDataUrl())
					|| ObjKit.nonEquals(request.getDataRequestMethod(), his.getDataRequestMethod())
					|| ObjKit.nonEquals(request.getDataRequestParams(), his.getDataRequestParams())
					|| ObjKit.nonEquals(request.getDataRequestHeaders(), his.getDataRequestHeaders())
			);
			Args.check(!isDataBindInfoChanged, ResponseCode.INHERITED_FCI_PROP_CANNOT_BE_MODIFIED);
		}

		if (DataBindFlagEnum.BIND_DATA.is(his.getDataBindFlag()) && DataBindFlagEnum.BIND_DATA.isNot(request.getDataBindFlag())) {
			if (Collections2.isNotEmpty(inheritedProps)) {
				throw new ArgoServerException(ResponseCode.EXISTS_INHERITED_FCI_PROPS);
			}
			// 删除数据绑定请求
			fciReqService.deleteByBelongId(FciReqBindTypeEnum.FCI, fciId);
		}
		if (DataBindFlagEnum.BIND_DATA.isNot(his.getDataBindFlag()) && DataBindFlagEnum.BIND_DATA.is(request.getDataBindFlag())) {
			if (Collections2.isNotEmpty(inheritedProps)) {
				throw new ArgoServerException(ResponseCode.EXISTS_INHERITED_FCI_PROPS);
			}
			// 新增数据绑定请求
			fciReqService.add(request, FciReqBindTypeEnum.FCI, fciId);
		}
		if (DataBindFlagEnum.BIND_DATA.is(his.getDataBindFlag()) && DataBindFlagEnum.BIND_DATA.is(request.getDataBindFlag())) {
			// 修改数据绑定请求
			fciReqService.modify(request, FciReqBindTypeEnum.FCI, fciId);
		}

		return fciService.modify(request);
	}


	public boolean delete(Long id) {
		FciItem item = fciService.getById(id);
		if (null == item) {
			throw new ArgoServerException(ResponseCode.FCI_NOT_EXISTS_ERROR);
		}
		List<PageItem> pageItems = pageService.findFciUsage(id);
		if (Collections2.isNotEmpty(pageItems)) {
			throw new ArgoServerException(ResponseCode.FCI_CANNOT_BE_DELETED_WHEN_USED);
		}

		List<FciPropItem> props = fciPropService.findEffectivePropsByFciId(id);
		if (Collections2.isNotEmpty(props)) {
			throw new ArgoServerException(ResponseCode.FCI_CANNOT_BE_DELETED_WHEN_PROPS_EFFECTIVE);
		}

		return fciService.deleteById(id);
	}


}
