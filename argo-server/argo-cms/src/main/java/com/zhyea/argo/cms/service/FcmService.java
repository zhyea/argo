package com.zhyea.argo.cms.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhyea.argo.cms.convert.FcmConverter;
import com.zhyea.argo.cms.model.item.FcmItem;
import com.zhyea.argo.cms.model.request.fcm.FcmAddRequest;
import com.zhyea.argo.cms.model.request.fcm.FcmEditRequest;
import com.zhyea.argo.cms.model.request.fcm.FcmQueryRequest;
import com.zhyea.argo.constants.NumConstants;
import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.data.dto.FcmDto;
import com.zhyea.argo.data.entity.cms.AppEntity;
import com.zhyea.argo.data.entity.cms.FcmEntity;
import com.zhyea.argo.data.mapper.cms.AppMapper;
import com.zhyea.argo.data.mapper.cms.FciMapper;
import com.zhyea.argo.data.mapper.cms.FcmMapper;
import com.zhyea.argo.except.ArgoServerException;
import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.codec.MD5;
import org.chobit.commons.group.E;
import org.chobit.commons.model.response.PageResult;
import org.chobit.commons.utils.JsonKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组件相关业务类
 *
 * @author robin
 */
@Slf4j
@Service
public class FcmService {


	private final FcmMapper fcmMapper;

	private final FcmConverter fcmConverter;

	private final FciMapper fciMapper;

	private final AppMapper appMapper;


	@Autowired
	public FcmService(FcmMapper fcmMapper, FcmConverter fcmConverter, FciMapper fciMapper, AppMapper appMapper) {
		this.fcmMapper = fcmMapper;
		this.fcmConverter = fcmConverter;
		this.fciMapper = fciMapper;
		this.appMapper = appMapper;
	}


	/**
	 * 新增组件模型
	 *
	 * @param request 新增组件模型请求
	 * @return 新增的组件模型id
	 */
	public Long add(FcmAddRequest request) {
		String uniqueCode = this.computeUniqCode(request);
		this.checkFcmExists(uniqueCode, null);
		this.checkAppExists(request.getAppId());

		FcmEntity entity = fcmConverter.addRequest2Entity(request);
		entity.setUniqCode(uniqueCode);
		fcmMapper.add(entity);
		return entity.getId();
	}


	/**
	 * 修改组件模型
	 *
	 * @param request 修改组件模型请求
	 * @return 是否修改成功
	 */
	public boolean edit(FcmEditRequest request) {
		String uniqCode = this.computeUniqCode(request);
		this.checkFcmExists(uniqCode, request.getFcmId());
		this.checkAppExists(request.getAppId());

		FcmEntity entity = fcmConverter.editRequest2Entity(request);
		entity.setUniqCode(uniqCode);
		int count = fcmMapper.modify(entity);
		return count == NumConstants.ONE;
	}


	/**
	 * 根据id获取组件模型
	 *
	 * @param id 组件模型id
	 * @return 组件模型
	 */
	public FcmItem getById(Long id) {
		FcmDto fcm = fcmMapper.getById(id);
		if (null == fcm) {
			return null;
		}
		return fcmConverter.dto2Item(fcm);
	}


	/**
	 * 根据id删除组件模型
	 *
	 * @param id 组件模型id
	 * @return 是否删除成功
	 */
	public boolean deleteById(Long id) {
		int fciCount = fciMapper.countByFcmId(id);
		if (fciCount > 0) {
			throw new ArgoServerException(ResponseCode.DELETE_WITH_FCI_ERROR);
		}

		int count = fcmMapper.deleteById(id);
		return count == NumConstants.ONE;
	}


	/**
	 * 分页查询获取组件模型列表
	 *
	 * @param request 组件模型查询请求
	 * @return 组件模型列表
	 */
	public PageResult<FcmItem> queryInPage(FcmQueryRequest request) {
		PageResult<FcmItem> result = new PageResult<>();

		try (Page<E> page = PageHelper.startPage(request.getPageNo(), request.getPageSize())) {
			List<FcmDto> list = fcmMapper.query(request.getAppId(), request.getKeyword());

			result.setData(fcmConverter.dtoList2ItemList(list));
			result.setTotal(page.getTotal());
			result.setPageNo(page.getPageNum());
			result.setPageSize(page.getPageSize());
		}

		return result;
	}


	/**
	 * 根据关键字获取组件模型列表
	 *
	 * @param request 查询请求
	 * @return 组件模型列表
	 */
	public List<FcmItem> findByKeyword(FcmQueryRequest request) {
		List<FcmDto> list = fcmMapper.query(request.getAppId(), request.getKeyword());
		return fcmConverter.dtoList2ItemList(list);
	}


	/**
	 * 检查组件模型是否存在
	 *
	 * @param uniqCode 组件模型唯一标识符
	 * @param id       组件模型id
	 */
	private void checkFcmExists(String uniqCode, Long id) {
		FcmEntity exists = fcmMapper.getByUniqCode(uniqCode, id);
		if (null != exists) {
			logger.info("FcmService.checkFcmExists 相同组件模型已存在 uniqueCode:{}, id:{}, name:{}", uniqCode, exists.getId(), exists.getName());
			throw new ArgoServerException(ResponseCode.FCM_DUPLICATE_ERROR.getCode(), "相同组件模型已存在:" + exists.getName());
		}
	}


	/**
	 * 检查应用是否存在
	 *
	 * @param appId 应用id
	 */
	private void checkAppExists(Long appId) {
		if (null == appId) {
			return;
		}
		AppEntity appEntity = appMapper.getById(appId);
		if (null == appEntity) {
			throw new ArgoServerException(ResponseCode.APP_ABSENT_ERROR);
		}
	}


	/**
	 * 计算组件唯一值
	 *
	 * @param request 组件基础信息
	 * @return 组件唯一值
	 */
	private String computeUniqCode(FcmAddRequest request) {
		FcmAddRequest o = fcmConverter.cloneWithoutUnnecessaryFields(request);
		String json = JsonKit.toJson(o);
		return MD5.encode(json);
	}


}
