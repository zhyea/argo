package com.zhyea.argo.cms.service;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhyea.argo.constants.NumConstants;
import com.zhyea.argo.cms.convert.FciConverter;
import com.zhyea.argo.entity.cms.FciEntity;
import com.zhyea.argo.cms.model.item.FciItem;
import com.zhyea.argo.cms.model.request.fci.FciAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciEditRequest;
import com.zhyea.argo.cms.model.request.fci.FciQueryRequest;
import com.zhyea.argo.mapper.cms.FciMapper;
import org.chobit.commons.model.response.PageResult;
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


    @Autowired
    public FciService(FciMapper fciMapper, FciConverter fciConverter) {
        this.fciMapper = fciMapper;
        this.fciConverter = fciConverter;
    }


    /**
     * 新增组件实例
     *
     * @param request 新增组件实例请求
     * @return 新增的组件实例id
     */
    public Long add(FciAddRequest request) {
        FciEntity entity = fciConverter.addRequest2Entity(request);
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
        FciEntity entity = fciMapper.getById(id);
        return fciConverter.entity2Item(entity);
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
        List<FciEntity> entityList = fciMapper.findByPageCode(pageCode);
        return fciConverter.entityList2ItemList(entityList);
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
            List<FciEntity> list = fciMapper.findByAppId(request.getAppId(), request.getKeyword());

            result.setData(fciConverter.entityList2ItemList(list));
            result.setPageNo(page.getPageNum());
            result.setPageSize(page.getPageSize());
            result.setTotal(page.getTotal());
        }

        return result;
    }



}
