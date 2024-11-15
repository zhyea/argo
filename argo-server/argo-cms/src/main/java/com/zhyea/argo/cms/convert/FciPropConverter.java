package com.zhyea.argo.cms.convert;

import com.zhyea.argo.cms.model.item.FciPropItem;
import com.zhyea.argo.cms.model.request.fci.FciPropAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciPropEditRequest;
import com.zhyea.argo.constants.enums.TimeRelateStatusEnum;
import com.zhyea.argo.data.entity.cms.FciPropEntity;
import org.mapstruct.*;

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
    FciPropItem entity2ItemSimply(FciPropEntity entity);


    @Named("entity2Item")
    default FciPropItem entity2Item(FciPropEntity entity) {
        if (null == entity) {
            return null;
        }

        FciPropItem item = entity2ItemSimply(entity);
        TimeRelateStatusEnum statusEnum =
                TimeRelateStatusEnum.analyzeStatus(item.getEffectivePeriodType(), item.getEffectiveStartTime(), item.getEffectiveEndTime());
        if (null == statusEnum) {
            return item;
        }
        item.setStatus(statusEnum.getCode());
        item.setStatusDesc(statusEnum.getDesc());
        return item;
    }


    /**
     * 实体列表转组件实例信息列表
     *
     * @param entityList 实体列表
     * @return 组件实例信息列表
     */
    @IterableMapping(qualifiedByName = "entity2Item")
    List<FciPropItem> listEntity2Item(List<FciPropEntity> entityList);


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
