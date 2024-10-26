package com.zhyea.argo.cms.convert;

import com.zhyea.argo.data.entity.cms.AppEntity;
import com.zhyea.argo.cms.model.item.AppItem;
import com.zhyea.argo.cms.model.request.app.AppAddRequest;
import com.zhyea.argo.cms.model.request.app.AppEditRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * 应用相关实体转换器
 *
 * @author robin
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppConverter {


	/**
	 * 新增请求转实体
	 *
	 * @param addRequest 新增请求
	 * @return 实体
	 */
	AppEntity addRequest2Entity(AppAddRequest addRequest);


	/**
	 * 修改请求转实体
	 *
	 * @param modifyRequest 修改请求
	 * @return 实体
	 */
	@Mapping(source = "appId", target = "id")
	AppEntity editRequest2Entity(AppEditRequest modifyRequest);


	/**
	 * 实体转应用项
	 *
	 * @param entity 实体
	 * @return 应用项
	 */
	AppItem entity2Item(AppEntity entity);


	/**
	 * 实体列表转应用项列表
	 *
	 * @param entityList 实体列表
	 * @return 应用项列表
	 */
	List<AppItem> entityList2ItemList(List<AppEntity> entityList);

}
