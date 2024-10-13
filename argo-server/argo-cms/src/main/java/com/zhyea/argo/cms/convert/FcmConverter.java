package com.zhyea.argo.cms.convert;

import com.zhyea.argo.cms.model.entity.FcmEntity;
import com.zhyea.argo.cms.model.item.FcmItem;
import com.zhyea.argo.cms.model.request.fcm.FcmAddRequest;
import com.zhyea.argo.cms.model.request.fcm.FcmModifyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Fcm转换器
 *
 * @author robin
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		uses = {CommonWorker.class, FcmWorker.class})
public interface FcmConverter {


	/**
	 * 新增请求对象转换为实体对象
	 *
	 * @param request 新增请求对象
	 * @return 实体对象
	 */
	@Mapping(target = "props", source = "props", qualifiedByName = "toJson")
	FcmEntity addRequestToEntity(FcmAddRequest request);


	/**
	 * 修改请求对象转换为实体对象
	 *
	 * @param request 修改请求对象
	 * @return 实体对象
	 */
	@Mapping(target = "props", source = "props", qualifiedByName = "toJson")
	@Mapping(target = "id", source = "fcmId")
	FcmEntity modifyRequestToEntity(FcmModifyRequest request);


	/**
	 * 实体对象转换为Item对象
	 *
	 * @param entity 实体对象
	 * @return Item对象
	 */
	@Mapping(target = "props", source = "props", qualifiedByName = "toPropItems")
	@Mapping(target = "fcmId", source = "id")
	FcmItem entityToItem(FcmEntity entity);


	/**
	 * 实体对象列表转换为Item对象列表
	 *
	 * @param entity 实体对象列表
	 * @return Item对象列表
	 */
	List<FcmItem> entityListToItemList(List<FcmEntity> entity);

}
