package com.zhyea.argo.cms.convert;

import com.zhyea.argo.model.request.fci.BaseFciRequest;
import com.zhyea.argo.constants.enums.FciReqBindTypeEnum;
import com.zhyea.argo.data.entity.cms.FciRequestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * FCI请求转换器
 *
 * @author robin
 * @since 2025/9/9 8:34
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FciReqConverter {


	default FciRequestEntity convert(BaseFciRequest request, FciReqBindTypeEnum bindType, Long id) {
		FciRequestEntity entity = new FciRequestEntity();

		entity.setBindType(bindType.code);
		entity.setBindId(id);
		entity.setDataUrl(request.getDataUrl());
		entity.setDataRequestMethod(request.getDataRequestMethod());
		entity.setDataRequestHeaders(request.getDataRequestHeaders());
		entity.setDataRequestParams(request.getDataRequestParams());

		entity.setOperatorCode(request.getOperatorCode());

		return entity;
	}


}
