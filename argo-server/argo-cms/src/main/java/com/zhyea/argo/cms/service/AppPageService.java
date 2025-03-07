package com.zhyea.argo.cms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhyea.argo.cms.convert.AppPageConverter;
import com.zhyea.argo.cms.model.item.AppPageItem;
import com.zhyea.argo.cms.model.request.app.AppPageAddRequest;
import com.zhyea.argo.cms.model.request.app.AppPageEditRequest;
import com.zhyea.argo.cms.model.request.app.AppPageQueryRequest;
import com.zhyea.argo.constants.NumConstants;
import com.zhyea.argo.data.entity.cms.AppPageEntity;
import com.zhyea.argo.data.mapper.cms.AppPageMapper;
import com.zhyea.argo.except.ArgoServerException;
import org.chobit.commons.group.E;
import org.chobit.commons.model.response.PageResult;
import org.chobit.commons.tools.ShortCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zhyea.argo.constants.ResponseCode.APP_PAGE_ALREADY_EXISTS_ERROR;

/**
 * 应用页面相关业务处理类
 *
 * @author robin
 */
@Service
public class AppPageService {


	private final AppPageMapper appPageMapper;

	private final AppPageConverter appPageConverter;


	@Autowired
	public AppPageService(AppPageMapper appPageMapper, AppPageConverter appPageConverter) {
		this.appPageMapper = appPageMapper;
		this.appPageConverter = appPageConverter;
	}


	/**
	 * 新增应用页面
	 *
	 * @param request 新增页面请求
	 * @return 新增的应用页面id
	 */
	public Long add(AppPageAddRequest request) {
		long count = appPageMapper.countByPageCode(request.getPageCode());
		if (count > 0) {
			throw new ArgoServerException(APP_PAGE_ALREADY_EXISTS_ERROR);
		}
		AppPageEntity entity = appPageConverter.addRequest2Entity(request);
		appPageMapper.add(entity);
		return entity.getId();
	}


	/**
	 * 修改应用页面
	 *
	 * @param request 修改页面请求
	 * @return 是否修改成功
	 */
	public boolean modify(AppPageEditRequest request) {
		AppPageEntity entity = appPageConverter.modifyRequest2Entity(request);
		int count = appPageMapper.modify(entity);
		return count == NumConstants.ONE;
	}


	/**
	 * 根据id获取应用页面
	 *
	 * @param pageId 应用页面id
	 * @return 应用页面
	 */
	public AppPageItem get(Long pageId) {
		AppPageEntity entity = appPageMapper.getById(pageId);
		return appPageConverter.entity2Item(entity);
	}


	/**
	 * 根据id删除应用页面
	 *
	 * @param pageId 应用页面id
	 * @return 是否删除成功
	 */
	public boolean delete(Long pageId) {
		int count = appPageMapper.deleteById(pageId);
		return count == NumConstants.ONE;
	}


	/**
	 * 根据应用id获取应用页面列表
	 *
	 * @param request 应用页面查询请求
	 * @return 应用页面列表
	 */
	public PageResult<AppPageItem> findByAppId(AppPageQueryRequest request) {
		PageResult<AppPageItem> result = new PageResult<>();

		try (Page<E> page = PageHelper.startPage(request.getPageNo(), request.getPageSize())) {
			List<AppPageEntity> list = appPageMapper.query(request.getAppId(), request.getKeyword());
			result.setData(appPageConverter.entityList2ItemList(list));
			result.setPageNo(page.getPageNum());
			result.setPageSize(page.getPageSize());
			result.setTotal(page.getTotal());
		}

		return result;
	}


	/**
	 * 生成页面编码
	 *
	 * @return 页面编码
	 */
	public String generatePageCode() {
		String pageCode = ShortCode.genUpper();
		long count = appPageMapper.countByPageCode(pageCode);
		while (count > 0) {
			pageCode = ShortCode.genUpper();
			count = appPageMapper.countByPageCode(pageCode);
		}
		return pageCode;
	}
}
