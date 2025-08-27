package com.zhyea.argo.cms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhyea.argo.cms.convert.FciPropConverter;
import com.zhyea.argo.cms.model.item.FciPropItem;
import com.zhyea.argo.cms.model.request.fci.FciPropAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciPropEditRequest;
import com.zhyea.argo.cms.model.request.fci.FciPropQueryRequest;
import com.zhyea.argo.constants.NumConstants;
import com.zhyea.argo.data.entity.cms.FciEntity;
import com.zhyea.argo.data.entity.cms.FciPropEntity;
import com.zhyea.argo.data.mapper.cms.FciPropMapper;
import org.chobit.commons.model.response.PageResult;
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
		FciPropEntity entity = propConverter.addRequest2Entity(request);
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
		FciPropEntity entity = propConverter.editRequest2Entity(request);
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
		return propConverter.entity2Item(entity);
	}


	/**
	 * 根据组件实例id获取组件属性列表
	 *
	 * @param request 组件属性查询请求
	 * @return 组件属性列表
	 */
	public PageResult<FciPropItem> findByFciId(FciPropQueryRequest request) {
		PageResult<FciPropItem> result = new PageResult<>();

		try (Page<FciEntity> page = PageHelper.startPage(request.getPageNo(), request.getPageSize())) {
			List<FciPropEntity> list = propMapper.findInPage(request.getFciId(), request.getKeyword());

			result.setData(propConverter.listEntity2Item(list));
			result.setPageNo(page.getPageNum());
			result.setPageSize(page.getPageSize());
			result.setTotal(page.getTotal());
		}

		return result;
	}


	/**
	 * 根据组件实例id获取当前有效的组件属性列表
	 *
	 * @param fciId 组件id
	 * @return 组件属性列表
	 */
	public List<FciPropItem> findValidByFciId(Long fciId) {
		List<FciPropEntity> list = propMapper.findValidByFciId(fciId);
		return propConverter.listEntity2Item(list);
	}


	/**
	 * 根据组件实例id和属性key获取组件属性
	 *
	 * @param fciId   组件id
	 * @param propKey 属性key
	 * @return 组件属性
	 */
	public List<FciPropItem> findByPropKey(Long fciId, String propKey) {
		List<FciPropEntity> list = propMapper.findByPropKey(fciId, propKey);
		return propConverter.listEntity2Item(list);
	}


	/**
	 * 关闭停用组件属性
	 *
	 * @param propId  组件属性ID
	 * @param propKey 属性key
	 * @return 是否关闭成功
	 */
	public boolean switchProp(Long propId, Integer propKey) {
		return propMapper.switchProp(propId, propKey);
	}


	/**
	 * 删除组件属性
	 *
	 * @param id 组件属性ID
	 * @return 是否删除成功
	 */
	public boolean delete(Long id) {
		return propMapper.deleteById(id) == NumConstants.ONE;
	}

}
