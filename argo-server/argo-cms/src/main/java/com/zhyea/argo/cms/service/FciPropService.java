package com.zhyea.argo.cms.service;

import com.zhyea.argo.constants.NumConstants;
import com.zhyea.argo.cms.convert.FciPropConverter;
import com.zhyea.argo.entity.cms.FciPropEntity;
import com.zhyea.argo.cms.model.item.FciPropItem;
import com.zhyea.argo.cms.model.request.fci.FciPropAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciPropEditRequest;
import com.zhyea.argo.mapper.cms.FciPropMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组件实例属性业务处理类
 *
 * @author robin
 */
@Service
public class FciPropService {


	private final FciPropMapper propMapper;

	private final FciPropConverter propConverter;


	@Autowired
	public FciPropService(FciPropMapper propMapper, FciPropConverter propConverter) {
		this.propMapper = propMapper;
		this.propConverter = propConverter;
	}


	/**
	 * 新增组件属性
	 *
	 * @param request 新增组件属性请求
	 * @return 新增的组件属性id
	 */
	public Long add(FciPropAddRequest request) {
		FciPropEntity entity = propConverter.addRequestToEntity(request);
		propMapper.add(entity);
		return entity.getId();
	}


	/**
	 * 修改组件属性
	 *
	 * @param request 修改组件属性请求
	 * @return 是否修改成功
	 */
	public boolean edit(FciPropEditRequest request) {
		FciPropEntity entity = propConverter.editRequestToEntity(request);
		int count = propMapper.edit(entity);
		return count == NumConstants.ONE;
	}


	/**
	 * 根据id获取组件属性
	 *
	 * @param id 组件属性id
	 * @return 组件属性
	 */
	public FciPropItem get(Long id) {
		FciPropEntity entity = propMapper.getById(id);
		return propConverter.entityToItem(entity);
	}


	/**
	 * 根据id删除组件属性
	 *
	 * @param id 组件属性id
	 * @return 是否删除成功
	 */
	public boolean delete(Long id) {
		int count = propMapper.deleteById(id);
		return count == NumConstants.ONE;
	}


	/**
	 * 根据组件实例id获取组件属性列表
	 *
	 * @param fciId 组件id
	 * @return 组件属性列表
	 */
	public List<FciPropItem> findByFciId(Long fciId) {
		List<FciPropEntity> list = propMapper.findByFciId(fciId);
		return propConverter.entityListToItemList(list);
	}


}
