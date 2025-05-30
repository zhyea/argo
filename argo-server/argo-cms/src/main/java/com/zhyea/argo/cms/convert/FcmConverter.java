package com.zhyea.argo.cms.convert;

import com.zhyea.argo.data.dto.FcmDto;
import com.zhyea.argo.data.entity.cms.FcmEntity;
import com.zhyea.argo.cms.model.item.FcmItem;
import com.zhyea.argo.cms.model.request.fcm.FcmAddRequest;
import com.zhyea.argo.cms.model.request.fcm.FcmEditRequest;
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
    FcmEntity addRequest2Entity(FcmAddRequest request);


    /**
     * 修改请求对象转换为实体对象
     *
     * @param request 修改请求对象
     * @return 实体对象
     */
    @Mapping(target = "props", source = "props", qualifiedByName = "toJson")
    @Mapping(target = "id", source = "fcmId")
    FcmEntity editRequest2Entity(FcmEditRequest request);


    /**
     * 实体对象转换为Item对象
     *
     * @param entity 实体对象
     * @return Item对象
     */
    @Mapping(target = "props", source = "props", qualifiedByName = "toPropItems")
    @Mapping(target = "fcmId", source = "id")
    FcmItem entity2Item(FcmEntity entity);


    /**
     * 实体对象列表转换为Item对象列表
     *
     * @param entity 实体对象列表
     * @return Item对象列表
     */
    List<FcmItem> entityList2ItemList(List<FcmEntity> entity);

    /**
     * 实体对象转换为Item对象
     *
     * @param dto 实体对象
     * @return Item对象
     */
    @Mapping(target = "props", source = "props", qualifiedByName = "toPropItems")
    @Mapping(target = "fcmId", source = "id")
    FcmItem dto2Item(FcmDto dto);


    /**
     * 实体对象列表转换为Item对象列表
     *
     * @param dtoList 实体对象列表
     * @return Item对象列表
     */
    List<FcmItem> dtoList2ItemList(List<FcmDto> dtoList);


    /**
     * 克隆请求对象，会忽略几个不必要的字段，主要用于计算uniqCode
     *
     * @param request 请求对象
     * @return 克隆后的请求对象
     */
    @Mapping(source = "name", target = "name", ignore = true)
    @Mapping(source = "icon", target = "icon", ignore = true)
    @Mapping(source = "remark", target = "remark", ignore = true)
    FcmAddRequest cloneWithoutUnnecessaryFields(FcmAddRequest request);

}
