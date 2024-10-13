package com.zhyea.argo.cms.service;

import com.zhyea.argo.cms.constants.NumConstants;
import com.zhyea.argo.cms.convert.FcmConverter;
import com.zhyea.argo.cms.model.entity.FcmEntity;
import com.zhyea.argo.cms.model.item.FcmItem;
import com.zhyea.argo.cms.model.request.fcm.FcmAddRequest;
import com.zhyea.argo.cms.model.request.fcm.FcmEditRequest;
import com.zhyea.argo.cms.service.mapper.FcmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 组件相关业务类
 *
 * @author robin
 */
@Service
public class FcmService {



	private final FcmMapper fcmMapper;

	private final FcmConverter fcmConverter;


	@Autowired
	public FcmService(FcmMapper fcmMapper, FcmConverter fcmConverter) {
		this.fcmMapper = fcmMapper;
		this.fcmConverter = fcmConverter;
	}



	public Long add(@RequestBody @Valid FcmAddRequest request){
		FcmEntity entity = fcmConverter.addRequest2Entity(request);
		 fcmMapper.add(entity);
		 return entity.getId();
	}



	public boolean modify(FcmEditRequest request){
		FcmEntity entity = fcmConverter.editRequest2Entity(request);
		int count = fcmMapper.modify(entity);
		return count == NumConstants.ONE;
	}



	public FcmItem getById(Long id){
		FcmEntity entity = fcmMapper.getById(id);
		return fcmConverter.entity2Item(entity);
	}



	public boolean deleteById(Long id){
		int count = fcmMapper.deleteById(id);
		return count == NumConstants.ONE;
	}




}
