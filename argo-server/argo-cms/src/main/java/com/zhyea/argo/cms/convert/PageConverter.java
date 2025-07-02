package com.zhyea.argo.cms.convert;

import com.zhyea.argo.cms.model.item.PageItem;
import com.zhyea.argo.data.entity.cms.PageEntity;
import com.zhyea.argo.cms.model.request.page.PageAddRequest;
import com.zhyea.argo.cms.model.request.page.PageEditRequest;
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
public interface PageConverter {


	/**
	 * 应用页面新增请求转换为实体
	 *
	 * @param addRequest 应用页面新增请求
	 * @return 实体
	 */
	PageEntity addRequest2Entity(PageAddRequest addRequest);


	/**
	 * 应用页面修改请求转换为实体
	 *
	 * @param modifyRequest 应用页面修改请求
	 * @return 实体
	 */
	PageEntity modifyRequest2Entity(PageEditRequest modifyRequest);


	/**
	 * 实体转换为应用页面信息
	 *
	 * @param entity 实体
	 * @return 应用页面信息
	 */
	@Mapping(source = "id", target = "pageId")
	PageItem entity2Item(PageEntity entity);


	/**
	 * 实体列表转换为应用页面信息列表
	 *
	 * @param entityList 实体列表
	 * @return 应用页面信息列表
	 */
	List<PageItem> entityList2ItemList(List<PageEntity> entityList);


}
