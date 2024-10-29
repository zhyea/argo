package com.zhyea.argo.cms.service;

import com.zhyea.argo.cms.convert.AppConverter;
import com.zhyea.argo.cms.model.item.AppItem;
import com.zhyea.argo.cms.model.request.app.AppAddRequest;
import com.zhyea.argo.cms.model.request.app.AppEditRequest;
import com.zhyea.argo.constants.NumConstants;
import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.data.entity.cms.AppEntity;
import com.zhyea.argo.data.mapper.cms.AppMapper;
import com.zhyea.argo.except.ArgoServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
