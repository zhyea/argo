package com.zhyea.argo.cms.action;

import com.zhyea.argo.biz.convert.FciPropConverter;
import com.zhyea.argo.model.item.FciItem;
import com.zhyea.argo.model.item.FciPropItem;
import com.zhyea.argo.model.request.fci.FciPropAddRequest;
import com.zhyea.argo.model.request.fci.FciPropEditRequest;
import com.zhyea.argo.biz.service.FciPropService;
import com.zhyea.argo.biz.service.FciReqService;
import com.zhyea.argo.biz.service.FciService;
import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.constants.enums.*;
import com.zhyea.argo.except.ArgoServerException;
import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.utils.Collections2;
import org.chobit.commons.utils.JsonKit;
import org.chobit.commons.utils.ObjKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.zhyea.argo.constants.NumConstants.ONE;

/**
 * 组件属性维护
 *
 * @author robin
 */
@Slf4j
@Component
public class FciPropMaintainAction {


	private final FciPropService fciPropService;
	private final FciPropConverter fciPropConverter;
	private final FciReqService fciReqService;
	private final FciService fciService;

	@Autowired
	public FciPropMaintainAction(FciPropService fciPropService,
	                             FciPropConverter fciPropConverter,
	                             FciService fciService,
	                             FciReqService fciReqService) {
		this.fciPropService = fciPropService;
		this.fciPropConverter = fciPropConverter;
		this.fciService = fciService;
		this.fciReqService = fciReqService;
	}


	/**
	 * 新增组件属性
	 *
	 * @param request 新增组件属性请求
	 * @return 新增的组件属性id
	 */
	@Transactional
	public Long add(FciPropAddRequest request) {
		checkForAdd(request);
		Long propId = fciPropService.add(request);
		if (DataBindFlagEnum.BIND_DATA.is(request.getDataBindFlag())) {
			fciReqService.add(request, FciReqBindTypeEnum.FCI_PROP, propId);
		}
		return propId;
	}


	/**
	 * 修改组件属性
	 *
	 * @param request 修改组件属性请求
	 * @return 是否修改成功
	 */
	@Transactional
	public boolean modify(FciPropEditRequest request) {
		checkForEdit(request);
		FciPropItem his = fciPropService.get(request.getId());
		if (DataBindFlagEnum.BIND_DATA.is(his.getDataBindFlag()) && DataBindFlagEnum.BIND_DATA.isNot(request.getDataBindFlag())) {
			// 删除数据绑定请求
			fciReqService.deleteByBelongId(FciReqBindTypeEnum.FCI_PROP, request.getId());
		}
		if (DataBindFlagEnum.BIND_DATA.isNot(his.getDataBindFlag()) && DataBindFlagEnum.BIND_DATA.is(request.getDataBindFlag())) {
			// 新增数据绑定请求
			fciReqService.add(request, FciReqBindTypeEnum.FCI_PROP, request.getId());
		}
		if (DataBindFlagEnum.BIND_DATA.is(his.getDataBindFlag()) && DataBindFlagEnum.BIND_DATA.is(request.getDataBindFlag())) {
			// 修改数据绑定请求
			fciReqService.modify(request, FciReqBindTypeEnum.FCI_PROP, request.getId());
		}
		return fciPropService.modify(request);
	}


	/**
	 * 通用的检查
	 *
	 * @param request 维护请求
	 */
	private void commonCheck(FciPropAddRequest request) {
		FciItem fciItem = fciService.getById(request.getFciId());
		if (null == fciItem) {
			throw new ArgoServerException(ResponseCode.FCI_NOT_EXISTS_ERROR);
		}
	}


	/**
	 * 组件属性新增件检查
	 *
	 * @param request 组件属性新增请求
	 */
	private void checkForAdd(FciPropAddRequest request) {
		commonCheck(request);

		// 取出同属性所有记录，检查起止时间是否存在重叠
		List<FciPropItem> all = fciPropService.findByPropKey(request.getFciId(), request.getPropKey());
		all.add(fciPropConverter.addRequest2Item(request));

		checkTimeOverlap(all);
	}


	/**
	 * 组件属性编辑检查
	 *
	 * @param request 组件属性编辑请求
	 */
	private void checkForEdit(FciPropEditRequest request) {
		commonCheck(request);

		FciPropItem history = fciPropService.get(request.getId());

		if (null == history || YesOrNo.YES.is(history.getDeleted())) {
			throw new ArgoServerException(ResponseCode.RECORD_NOT_EXISTS_ERROR);
		}
		// 已过期的记录不允许修改
		if (TimeRelateStatusEnum.EXPIRED.is(history.getStatus())) {
			throw new ArgoServerException(ResponseCode.EDIT_EXPIRED_RECORD_ERROR);
		}

		// 已生效记录不允许修改开始时间
		if (TimeRelateStatusEnum.IN_EFFECT.is(history.getStatus())
				&& ObjKit.nonEquals(history.getEffectiveStartTime(), request.getEffectiveStartTime())) {
			throw new ArgoServerException(ResponseCode.FCI_PROP_EFFECTIVE_START_TIME_NOT_ALLOW_EDIT);
		}

		// 固定属性不允许修改
		if (ObjKit.nonEquals(history.getFciId(), request.getFciId())
				|| ObjKit.nonEquals(history.getPropKey(), request.getPropKey())) {
			throw new ArgoServerException(ResponseCode.MODIFY_FIXED_INFO_ERROR);
		}

		// 取出同属性所有记录，检查起止时间是否存在重叠
		List<FciPropItem> all = fciPropService.findByPropKey(request.getFciId(), request.getPropKey());
		FciPropItem his = all.stream().filter(e -> Objects.equals(e.getId(), request.getId())).findFirst().orElse(null);

		// 需要存在历史记录，不然编辑没有意义
		if (null == his) {
			throw new ArgoServerException(ResponseCode.RECORD_NOT_EXISTS_ERROR);
		}

		// 属性key不允许修改
		if (ObjKit.nonEquals(his.getPropKey(), his.getPropKey())) {
			throw new ArgoServerException(ResponseCode.FCI_PROP_KEY_NOT_ALLOWED_EDIT);
		}

		// 历史记录已过期不允许做任何调整
		if (TimeRelateStatusEnum.EXPIRED.is(his.getStatus())) {
			throw new ArgoServerException(ResponseCode.EDIT_EXPIRED_RECORD_ERROR);
		}
		// 历史记录已生效不允许修改开始时间、数据绑定标记、数据URL、数据选择器
		this.checkInEffectivePropEdit(request, his);

		// 历史记录未生效不做任何限制

		all = all.stream().filter(e -> ObjKit.nonEquals(e.getId(), request.getId()))
				.collect(Collectors.toList());
		FciPropItem tmp = fciPropConverter.editRequest2Item(request);
		all.add(tmp);

		checkTimeOverlap(all);
	}


	private void checkInEffectivePropEdit(FciPropEditRequest curr, FciPropItem his) {
		if (TimeRelateStatusEnum.IN_EFFECT.isNot(his.getStatus())) {
			return;
		}

		if (ObjKit.nonEquals(curr.getEffectiveStartTime(), his.getEffectiveStartTime())
				|| ObjKit.nonEquals(curr.getDataBindFlag(), his.getDataBindFlag())
				|| ObjKit.nonEquals(curr.getDataUrl(), his.getDataUrl())
				|| ObjKit.nonEquals(curr.getPropValueSelector(), his.getPropValueSelector())) {
			throw new ArgoServerException(ResponseCode.FCI_PROP_EFFECTIVE_DATA_NOT_ALLOW_EDIT);
		}
	}


	/**
	 * 检查组件属性是否存在重叠（只考虑重叠不考虑断档，断档则不显示）
	 *
	 * @param propItems 组件属性列表
	 */
	private void checkTimeOverlap(List<FciPropItem> propItems) {

		// 空记录无需检查
		if (Collections2.isEmpty(propItems)) {
			return;
		}

		// 只有一行记录时不可能存在交叉
		if (propItems.size() == ONE) {
			return;
		}

		// 过滤出短期有效记录并做排序
		List<FciPropItem> tmp = propItems.stream()
				.filter(e -> EffectivePeriodTypeEnum.FIXED_TERM.is(e.getEffectivePeriodType()))
				.sorted(Comparator.comparing(FciPropItem::getEffectiveStartTime)).collect(Collectors.toList());

		// 存在多条长期有效记录
		if (Collections2.isEmpty(tmp)) {
			throw new ArgoServerException(ResponseCode.FCI_PROP_EFFECTIVE_TIME_OVERLAP);
		}

		// 是否存在长期有效记录
		boolean existsLongTerm = propItems.size() > tmp.size();

		LocalDateTime t = null;
		for (FciPropItem item : tmp) {
			if (item.getEffectiveStartTime().isAfter(item.getEffectiveEndTime())
					|| item.getEffectiveStartTime().equals(item.getEffectiveEndTime())) {
				// 起止时间重叠
				logger.error("FciPropMaintainAction.checkTimeOverlap 组件属性起止时间有误 item:{}", JsonKit.toJson(item));
				throw new ArgoServerException(ResponseCode.FCI_PROP_EFFECTIVE_TIME_OVERLAP);
			}

			if (null != t && t.isAfter(item.getEffectiveStartTime())) {
				// 上次的结束时间大于本次的开始时间，说明存在重叠
				throw new ArgoServerException(ResponseCode.FCI_PROP_EFFECTIVE_TIME_OVERLAP);
			} else if (null == t) {
				t = item.getEffectiveEndTime();
			}

			if (item.getEffectiveEndTime().isAfter(LocalDateTime.now()) && existsLongTerm) {
				// 存在长期有效记录，且存在短期有效记录的结束时间大于当前时间，说明存在重叠
				throw new ArgoServerException(ResponseCode.FCI_PROP_EFFECTIVE_TIME_OVERLAP);
			}
		}
		// --------- ---------- -----------
	}


	/**
	 * 根据id删除组件属性
	 *
	 * @param id 组件属性id
	 * @return 是否删除成功
	 */
	public boolean delete(Long id) {
		FciPropItem item = fciPropService.get(id);
		if (null == item) {
			return true;
		}

		Integer status = item.getStatus();
		if (TimeRelateStatusEnum.EXPIRED.is(status)) {
			throw new ArgoServerException(ResponseCode.EXPIRED_FCI_PROP_CANNOT_BE_DELETED);
		}
		if (TimeRelateStatusEnum.IN_EFFECT.is(status)) {
			throw new ArgoServerException(ResponseCode.EFFECTIVE_FCI_PROP_CANNOT_BE_DELETED);
		}
		return fciPropService.delete(id);
	}
}
