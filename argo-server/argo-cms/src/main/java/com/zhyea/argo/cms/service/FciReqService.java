package com.zhyea.argo.cms.service;

import com.zhyea.argo.cms.convert.FciReqConverter;
import com.zhyea.argo.model.request.fci.BaseFciRequest;
import com.zhyea.argo.constants.enums.FciReqBindTypeEnum;
import com.zhyea.argo.data.entity.cms.FciRequestEntity;
import com.zhyea.argo.data.mapper.cms.FciRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FCI请求业务类
 *
 * @author robin
 * @since 2025/9/9 8:13
 */
@Service
public class FciReqService {

	private final FciRequestMapper fciRequestMapper;
	private final FciReqConverter fciReqConverter;

	@Autowired
	public FciReqService(FciRequestMapper fciRequestMapper, FciReqConverter fciReqConverter) {
		this.fciRequestMapper = fciRequestMapper;
		this.fciReqConverter = fciReqConverter;
	}


	public Long add(BaseFciRequest request, FciReqBindTypeEnum belongType, Long id) {
		FciRequestEntity entity = fciReqConverter.convert(request, belongType, id);
		return fciRequestMapper.add(entity);
	}


	public void modify(BaseFciRequest request, FciReqBindTypeEnum belongType, Long id) {
		FciRequestEntity entity = fciReqConverter.convert(request, belongType, id);
		fciRequestMapper.modify(entity);
	}


	public FciRequestEntity getByBelongId(FciReqBindTypeEnum belongType, Long id) {
		return fciRequestMapper.getByBindId(belongType.getCode(), id);
	}


	public boolean deleteByBelongId(FciReqBindTypeEnum belongType, Long id) {
		return fciRequestMapper.deleteByBindId(belongType.getCode(), id);
	}
}
