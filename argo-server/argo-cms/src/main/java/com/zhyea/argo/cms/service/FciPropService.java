package com.zhyea.argo.cms.service;

import com.zhyea.argo.constants.NumConstants;
import com.zhyea.argo.cms.convert.FciPropConverter;
import com.zhyea.argo.data.entity.cms.FciPropEntity;
import com.zhyea.argo.cms.model.item.FciPropItem;
import com.zhyea.argo.cms.model.request.fci.FciPropAddRequest;
import com.zhyea.argo.cms.model.request.fci.FciPropEditRequest;
import com.zhyea.argo.data.mapper.cms.FciPropMapper;
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
        return propConverter.entityList2ItemList(list);
    }


    /**
     * 根据组件实例id获取当前有效的组件属性列表
     *
     * @param fciId 组件id
     * @return 组件属性列表
     */
    public List<FciPropItem> findValidByFciId(Long fciId) {
        List<FciPropEntity> list = propMapper.findValidByFciId(fciId);
        return propConverter.entityList2ItemList(list);
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
        return propConverter.entityList2ItemList(list);
    }


    /**
     * 关闭停用组件属性
     *
     * @param fciId   组件ID
     * @param propKey 属性key
     * @return 是否关闭成功
     */
    public boolean closePropKey(Long fciId, String propKey) {
        return propMapper.closePropKey(fciId, propKey);
    }

}
