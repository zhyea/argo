package com.zhyea.argo.cms.convert;

import com.zhyea.argo.cms.model.item.FciPropItem;
import com.zhyea.argo.cms.model.request.fci.FciPropAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciPropEditRequest;
import com.zhyea.argo.constants.enums.TimeRelateStatusEnum;
import com.zhyea.argo.data.entity.cms.FciPropEntity;
import org.chobit.commons.utils.Collections2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.LinkedList;
import java.util.List;

/**
 * 组件属性转换器
 *
 * @author robin
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FciPropConverter {


	/**
	 * 新增组件实例请求转实体
	 *
	 * @param request 新增组件实例请求
	 * @return 实体
	 */
	FciPropEntity addRequest2Entity(FciPropAddRequest request);


	/**
	 * 修改组件实例请求转实体
	 *
	 * @param request 修改组件实例请求
	 * @return 实体
	 */
	@Mapping(target = "id", source = "propId")
	FciPropEntity editRequest2Entity(FciPropEditRequest request);


	/**
	 * 实体转组件实例信息
	 *
	 * @param entity 实体
	 * @return 组件实例信息
	 */
	FciPropItem entity2Item(FciPropEntity entity);


	/**
	 * 实体列表转组件实例信息列表
	 *
	 * @param entityList 实体列表
	 * @return 组件实例信息列表
	 */
	List<FciPropItem> listEntity2ItemSimply(List<FciPropEntity> entityList);


	/**
	 * 实体列表转组件实例信息列表，并设置状态信息
	 *
	 * @param entityList 实体列表
	 * @return 组件实例信息列表
	 */
	default List<FciPropItem> listEntity2Item(List<FciPropEntity> entityList) {
		if (Collections2.isEmpty(entityList)) {
			return new LinkedList<>();
		}

		List<FciPropItem> list = listEntity2ItemSimply(entityList);
		for (FciPropItem e : list) {
			TimeRelateStatusEnum statusEnum =
					TimeRelateStatusEnum.analyzeStatus(e.getEffectivePeriodType(), e.getEffectiveStartTime(), e.getEffectiveEndTime());
			if (null == statusEnum) {
				continue;
			}
			e.setStatus(statusEnum.getCode());
			e.setStatusDesc(statusEnum.getDesc());
		}

		return list;
	}


	/**
	 * 新增组件实例请求转组件实例信息
	 *
	 * @param request 新增组件实例请求
	 * @return 组件实例信息
	 */
	FciPropItem addRequest2Item(FciPropAddRequest request);


	/**
	 * 修改组件实例请求转组件实例信息
	 *
	 * @param request 修改组件实例请求
	 * @return 组件实例信息
	 */
	@Mapping(target = "id", source = "propId")
	FciPropItem editRequest2Item(FciPropEditRequest request);
}
