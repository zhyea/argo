package com.zhyea.argo.cms.convert;

import com.zhyea.argo.cms.model.entity.AppPageEntity;
import com.zhyea.argo.cms.model.item.AppPageItem;
import com.zhyea.argo.cms.model.request.app.AppPageAddRequest;
import com.zhyea.argo.cms.model.request.app.AppPageEditRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * 应用页面信息转换
 *
 * @author robin
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppPageConverter {


	/**
	 * 应用页面新增请求转换为实体
	 *
	 * @param addRequest 应用页面新增请求
	 * @return 实体
	 */
	AppPageEntity addRequest2Entity(AppPageAddRequest addRequest);


	/**
	 * 应用页面修改请求转换为实体
	 *
	 * @param modifyRequest 应用页面修改请求
	 * @return 实体
	 */
	@Mapping(source = "pageId", target = "id")
	AppPageEntity modifyRequest2Entity(AppPageEditRequest modifyRequest);


	/**
	 * 实体转换为应用页面信息
	 *
	 * @param entity 实体
	 * @return 应用页面信息
	 */
	@Mapping(source = "id", target = "pageId")
	AppPageItem entity2Item(AppPageEntity entity);


	/**
	 * 实体列表转换为应用页面信息列表
	 *
	 * @param entityList 实体列表
	 * @return 应用页面信息列表
	 */
	List<AppPageItem> entityList2ItemList(List<AppPageEntity> entityList);


}
