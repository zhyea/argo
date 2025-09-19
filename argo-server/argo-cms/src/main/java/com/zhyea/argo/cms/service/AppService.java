package com.zhyea.argo.cms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhyea.argo.cms.convert.AppConverter;
import com.zhyea.argo.model.item.AppItem;
import com.zhyea.argo.model.request.app.AppAddRequest;
import com.zhyea.argo.model.request.app.AppEditRequest;
import com.zhyea.argo.model.request.app.AppPageRequest;
import com.zhyea.argo.model.request.app.AppQueryRequest;
import com.zhyea.argo.constants.NumConstants;
import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.data.entity.cms.AppEntity;
import com.zhyea.argo.data.mapper.cms.AppMapper;
import com.zhyea.argo.except.ArgoServerException;
import org.chobit.commons.model.response.PageResult;
import org.chobit.commons.tools.ShortCode;
import org.chobit.commons.utils.Collections2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author robin
 */
@Service
public class AppService {


	private final AppMapper appMapper;
	private final AppConverter appConverter;


	@Autowired
	public AppService(AppMapper appMapper, AppConverter appConverter) {
		this.appMapper = appMapper;
		this.appConverter = appConverter;
	}


	/**
	 * 新增应用记录
	 *
	 * @param request 应用信息
	 * @return 应用id
	 */
	public Long add(AppAddRequest request) {
		int count = appMapper.countByAppCode(request.getAppCode());
		if (count > 0) {
			throw new ArgoServerException(ResponseCode.APP_DUPLICATE_ERROR);
		}

		AppEntity entity = appConverter.addRequest2Entity(request);
		appMapper.add(entity);
		return entity.getId();
	}


	/**
	 * 修改应用记录
	 *
	 * @param request 应用信息
	 * @return 影响行数
	 */
	public boolean edit(AppEditRequest request) {
		AppEntity entity = appConverter.editRequest2Entity(request);
		int count = appMapper.modify(entity);
		return NumConstants.ONE == count;
	}


	/**
	 * 根据id获取应用信息
	 *
	 * @param appId 应用id
	 * @return 应用信息
	 */
	public AppItem get(Long appId) {
		AppEntity entity = appMapper.getById(appId);
		return appConverter.entity2Item(entity);
	}


	/**
	 * 根据id删除应用记录
	 *
	 * @param appId 应用id
	 * @return 影响行数
	 */
	public Boolean delete(Long appId) {
		int count = appMapper.deleteById(appId);
		return NumConstants.ONE == count;
	}


	/**
	 * 获取所有应用记录
	 *
	 * @return 应用列表
	 */
	public List<AppItem> findAll() {
		List<AppEntity> list = appMapper.findAll();
		return appConverter.entityList2ItemList(list);
	}


	/**
	 * 分页获取应用记录
	 *
	 * @param request 分页请求
	 * @return 应用列表
	 */
	public PageResult<AppItem> findInPage(AppPageRequest request) {

		PageResult<AppItem> result = new PageResult<>();

		try (Page<AppItem> page = PageHelper.startPage(request.getPageNo(), request.getPageSize())) {
			List<AppEntity> list = appMapper.query(request.getKeyword());

			result.setData(appConverter.entityList2ItemList(list));
			result.setPageNo(page.getPageNum());
			result.setPageSize(page.getPageSize());
			result.setTotal(page.getTotal());
		}

		return result;
	}


	/**
	 * 根据关键字查询应用
	 *
	 * @param request 查询请求
	 * @return 应用列表
	 */
	public List<AppItem> query(AppQueryRequest request) {
		List<AppEntity> list = appMapper.query(request.getKeyword());
		if (Collections2.isEmpty(list)) {
			return new LinkedList<>();
		} else {
			return appConverter.entityList2ItemList(list);
		}
	}


	/**
	 * 生成页面编码
	 *
	 * @return 页面编码
	 */
	public String generateAppCode() {
		String appCode = ShortCode.genUpper();
		long count = appMapper.countByAppCode(appCode);
		while (count > 0) {
			appCode = ShortCode.genUpper();
			count = appMapper.countByAppCode(appCode);
		}
		return appCode;
	}
}
