package com.zhyea.argo.cms.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhyea.argo.cms.convert.FciConverter;
import com.zhyea.argo.cms.model.item.FciItem;
import com.zhyea.argo.cms.model.item.FcmItem;
import com.zhyea.argo.cms.model.request.fci.FciAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciEditRequest;
import com.zhyea.argo.cms.model.request.fci.FciQueryRequest;
import com.zhyea.argo.constants.NumConstants;
import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.data.dto.FciDto;
import com.zhyea.argo.data.entity.cms.FciEntity;
import com.zhyea.argo.data.mapper.cms.FciMapper;
import com.zhyea.argo.except.ArgoServerException;
import com.zhyea.argo.tools.auth.AuthContext;
import org.chobit.commons.model.response.PageResult;
import org.chobit.commons.tools.ShortCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 组件实例相关业务类
 *
 * @author robin
 */
@Service
public class FciService {


	private final FciMapper fciMapper;
	private final FciConverter fciConverter;
	private final FcmService fcmService;


	@Autowired
	public FciService(FciMapper fciMapper, FciConverter fciConverter, FcmService fcmService) {
		this.fciMapper = fciMapper;
		this.fciConverter = fciConverter;
		this.fcmService = fcmService;
	}


	/**
	 * 新增组件实例
	 *
	 * @param request 新增组件实例请求
	 * @return 新增的组件实例id
	 */
	public Long add(FciAddRequest request) {
		FcmItem fcmItem = fcmService.getById(request.getFcmId());
		if (null == fcmItem) {
			throw new ArgoServerException(ResponseCode.FCM_NOT_EXISTS_ERROR);
		}

		FciEntity entity = fciConverter.addRequest2Entity(request);
		entity.setFciCode(ShortCode.genUpper());
		entity.setOperatorCode(AuthContext.getUsername());

		fciMapper.add(entity);
		return entity.getId();
	}


	/**
	 * 修改组件实例
	 *
	 * @param request 修改组件实例请求
	 * @return 是否修改成功
	 */
	public boolean edit(FciEditRequest request) {
		FciEntity entity = fciConverter.modifyRequest2Entity(request);
		int count = fciMapper.modify(entity);
		return count == NumConstants.ONE;
	}


	/**
	 * 根据id获取组件实例
	 *
	 * @param id 组件实例id
	 * @return 组件实例
	 */
	public FciItem getById(Long id) {
		FciDto dto = fciMapper.getById(id);
		return fciConverter.dto2Item(dto);
	}


	/**
	 * 根据id删除组件实例
	 *
	 * @param id 组件实例id
	 * @return 是否删除成功
	 */
	public boolean deleteById(Long id) {
		int count = fciMapper.deleteById(id);
		return count == NumConstants.ONE;
	}


	/**
	 * 根据页面id获取组件实例列表
	 *
	 * @param pageCode 页面code
	 * @return 组件实例列表
	 */
	public List<FciItem> findByPageCode(String pageCode) {
		List<FciDto> entityList = fciMapper.findByPageCode(pageCode);
		return fciConverter.dtoList2ItemList(entityList);
	}


	/**
	 * 分页查询组件实例
	 *
	 * @param request 分页查询请求
	 * @return 分页查询结果
	 */
	public PageResult<FciItem> findInPage(FciQueryRequest request) {
		PageResult<FciItem> result = new PageResult<>();

		try (Page<FciEntity> page = PageHelper.startPage(request.getPageNo(), request.getPageSize())) {
			List<FciDto> list = fciMapper.findByAppId(request.getAppId(), request.getKeyword());

			result.setData(fciConverter.dtoList2ItemList(list));
			result.setPageNo(page.getPageNum());
			result.setPageSize(page.getPageSize());
			result.setTotal(page.getTotal());
		}

		return result;
	}


}
