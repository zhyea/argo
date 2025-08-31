package com.zhyea.argo.cms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhyea.argo.cms.convert.FciConverter;
import com.zhyea.argo.cms.convert.PageConverter;
import com.zhyea.argo.cms.model.item.FciItem;
import com.zhyea.argo.cms.model.item.PageItem;
import com.zhyea.argo.cms.model.request.page.*;
import com.zhyea.argo.constants.NumConstants;
import com.zhyea.argo.data.entity.cms.FciEntity;
import com.zhyea.argo.data.entity.cms.PageEntity;
import com.zhyea.argo.data.mapper.cms.PageFciMapper;
import com.zhyea.argo.data.mapper.cms.PageMapper;
import com.zhyea.argo.except.ArgoServerException;
import org.chobit.commons.group.E;
import org.chobit.commons.model.response.PageResult;
import org.chobit.commons.tools.ShortCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.zhyea.argo.constants.ResponseCode.APP_PAGE_ALREADY_EXISTS_ERROR;

/**
 * 应用页面相关业务处理类
 *
 * @author robin
 */
@Service
public class PageService {


	private final PageMapper pageMapper;
	private final PageConverter pageConverter;
	private final PageFciMapper pageFciMapper;
	private final FciConverter fciConverter;


	@Autowired
	public PageService(PageMapper pageMapper, PageConverter pageConverter, PageFciMapper pageFciMapper, FciConverter fciConverter) {
		this.pageMapper = pageMapper;
		this.pageConverter = pageConverter;
		this.pageFciMapper = pageFciMapper;
		this.fciConverter = fciConverter;
	}


	/**
	 * 新增应用页面
	 *
	 * @param request 新增页面请求
	 * @return 新增的应用页面id
	 */
	public Long add(PageAddRequest request) {
		long count = pageMapper.countByPageCode(request.getPageCode());
		if (count > 0) {
			throw new ArgoServerException(APP_PAGE_ALREADY_EXISTS_ERROR);
		}
		PageEntity entity = pageConverter.addRequest2Entity(request);
		pageMapper.add(entity);
		return entity.getId();
	}


	/**
	 * 修改应用页面
	 *
	 * @param request 修改页面请求
	 * @return 是否修改成功
	 */
	public boolean modify(PageEditRequest request) {
		PageEntity entity = pageConverter.modifyRequest2Entity(request);
		int count = pageMapper.modify(entity);
		return count == NumConstants.ONE;
	}


	/**
	 * 根据id获取应用页面
	 *
	 * @param pageId 应用页面id
	 * @return 应用页面
	 */
	public PageItem get(Long pageId) {
		PageEntity entity = pageMapper.getById(pageId);
		return pageConverter.entity2Item(entity);
	}


	/**
	 * 根据id删除应用页面
	 *
	 * @param pageId 应用页面id
	 * @return 是否删除成功
	 */
	public boolean delete(Long pageId) {
		// TODO 删除之前检查该应用页面是否被关联到其他组件实例中
		int count = pageMapper.deleteById(pageId);
		return count == NumConstants.ONE;
	}


	/**
	 * 根据应用id获取应用页面列表
	 *
	 * @param request 应用页面查询请求
	 * @return 应用页面列表
	 */
	public PageResult<PageItem> findByAppId(PageQueryRequest request) {
		PageResult<PageItem> result = new PageResult<>();

		try (Page<E> page = PageHelper.startPage(request.getPageNo(), request.getPageSize())) {
			List<PageEntity> list = pageMapper.query(request.getAppId(), request.getKeyword());
			result.setData(pageConverter.entityList2ItemList(list));
			result.setPageNo(page.getPageNum());
			result.setPageSize(page.getPageSize());
			result.setTotal(page.getTotal());
		}

		return result;
	}


	/**
	 * 查询应用页面
	 *
	 * @param request 查询请求
	 * @return 返回应用页面列表
	 */
	public List<PageItem> queryAppPages(PageListQueryRequest request) {
		List<PageEntity> list = pageMapper.query(request.getAppId(), request.getKeyword());
		return pageConverter.entityList2ItemList(list);
	}


	/**
	 * 生成页面编码
	 *
	 * @return 页面编码
	 */
	public String generatePageCode() {
		String pageCode = ShortCode.genUpper();
		long count = pageMapper.countByPageCode(pageCode);
		while (count > 0) {
			pageCode = ShortCode.genUpper();
			count = pageMapper.countByPageCode(pageCode);
		}
		return pageCode;
	}


	/**
	 * 映射页面组件实例
	 *
	 * @param request 页面组件实例映射请求
	 * @return 是否映射成功
	 */
	public boolean mapFci(PageFciMapRequest request) {
		Integer count = pageFciMapper.batchAdd(request.getPageId(), request.getFciIdList());
		return request.getFciIdList().size() == count;
	}


	/**
	 * 查询使用指定组件的页面
	 *
	 * @param fciId 组件实例id
	 * @return 使用指定组件的页面列表
	 */
	public List<PageItem> findFciUsage(Long fciId) {
		List<PageEntity> list = pageFciMapper.findFciUsage(fciId);
		return pageConverter.entityList2ItemList(list);
	}


	/**
	 * 查询可用的组件
	 *
	 * @param appId 应用id
	 * @return 未使用的组件列表
	 */
	public List<FciItem> findAvailableFci(Long appId, Long pageId) {
		List<FciEntity> list = pageFciMapper.findAvailableFci(appId, pageId);

		Set<Long> idSet = new HashSet<>();
		List<FciItem> result = new ArrayList<>(list.size());

		for (FciEntity entity : list) {
			if (idSet.contains(entity.getId())) {
				continue;
			}
			idSet.add(entity.getId());
			result.add(fciConverter.entity2Item(entity));
		}

		return result;
	}


	/**
	 * 根据页面id获取页面组件实例列表
	 *
	 * @param pageId 页面id
	 * @return 页面组件实例列表
	 */
	public List<FciItem> findByPageId(Long pageId) {
		List<FciEntity> list = pageFciMapper.findByPageId(pageId);
		return fciConverter.entityList2ItemList(list);
	}
}
