package com.zhyea.argo.cms.convert;

import com.zhyea.argo.cms.model.entity.FciPropEntity;
import com.zhyea.argo.cms.model.item.FciPropItem;
import com.zhyea.argo.cms.model.request.fci.FciPropAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciPropModifyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

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
	FciPropEntity addRequestToEntity(FciPropAddRequest request);


	/**
	 * 修改组件实例请求转实体
	 *
	 * @param request 修改组件实例请求
	 * @return 实体
	 */
	@Mapping(target = "id", source = "propId")
	FciPropEntity modifyRequestToEntity(FciPropModifyRequest request);


	/**
	 * 实体转组件实例信息
	 *
	 * @param entity 实体
	 * @return 组件实例信息
	 */
	FciPropItem entityToItem(FciPropEntity entity);


	/**
	 * 实体列表转组件实例信息列表
	 *
	 * @param entityList 实体列表
	 * @return 组件实例信息列表
	 */
	List<FciPropItem> entityListToItemList(List<FciPropEntity> entityList);


}
