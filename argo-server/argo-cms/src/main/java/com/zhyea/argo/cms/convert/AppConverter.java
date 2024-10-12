package com.zhyea.argo.cms.convert;

import com.zhyea.argo.cms.model.entity.AppEntity;
import com.zhyea.argo.cms.model.request.app.AppAddRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppConverter {


    AppEntity request2Entity(AppAddRequest addRequest);


}
