package com.zhyea.argo.cms.convert;

import com.zhyea.argo.data.entity.cms.FciEntity;
import com.zhyea.argo.cms.model.item.FciItem;
import com.zhyea.argo.cms.model.request.fci.FciAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciEditRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * 组件实例转换
 *
 * @author robin
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		uses = {CommonWorker.class})
public interface FciConverter {


	/**
	 * 新增请求对象转换为实体对象
	 *
	 * @param request 新增请求对象
	 * @return 实体对象
	 */
	@Mapping(source = "effectiveTimeRange", target = "effectiveStartTime", qualifiedByName = "takeStartTime")
	@Mapping(source = "effectiveTimeRange", target = "effectiveEndTime", qualifiedByName = "takeEndTime")
	FciEntity addRequest2Entity(FciAddRequest request);


	/**
	 * 修改请求对象转换为实体对象
	 *
	 * @param request 修改请求对象
	 * @return 实体对象
	 */
	@Mapping(source = "effectiveTimeRange", target = "effectiveStartTime", qualifiedByName = "takeStartTime")
	@Mapping(source = "effectiveTimeRange", target = "effectiveEndTime", qualifiedByName = "takeEndTime")
	@Mapping(source = "fciId", target = "id")
	FciEntity modifyRequest2Entity(FciEditRequest request);


	/**
	 * 实体对象转换为Item对象
	 *
	 * @param entity 实体对象
	 * @return Item对象
	 */
	FciItem entity2Item(FciEntity entity);


	/**
	 * 实体对象列表转换为Item对象列表
	 *
	 * @param entityList 实体对象列表
	 * @return Item对象列表
	 */
	List<FciItem> entityList2ItemList(List<FciEntity> entityList);

}
