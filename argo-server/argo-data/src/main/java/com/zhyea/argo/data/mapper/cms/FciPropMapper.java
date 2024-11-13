package com.zhyea.argo.data.mapper.cms;

import com.zhyea.argo.data.entity.cms.FciPropEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 组件属性 mapper
 *
 * @author robin
 */
@Mapper
public interface FciPropMapper {


    /**
     * 新增组件属性记录
     *
     * @param entity 组件属性数据
     */
    @Insert({
            "insert into ag_cms_fci_prop (fci_id, prop_key, prop_value, prop_value_selector",
            "effective_period_type, effective_start_time, effective_end_time, remark)",
            "values",
            "(#{e.fciId}, #{e.propKey}, #{e.propValue}, #{e.propValueSelector},",
            "#{e.effectivePeriodType}, #{e.effectiveStartTime}, #{e.effectiveEndTime}, #{e.remark})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(@Param("e") FciPropEntity entity);


    /**
     * 修改组件属性记录
     *
     * @param entity 组件属性数据
     * @return 修改条数
     */
    @Update({
            "update ag_cms_fci_prop set",
            "fci_id=#{e.fciId}, prop_key=#{e.propKey}, prop_value=#{e.propValue}, prop_value_selector=#{e.propValueSelector},",
            "effective_period_type=#{e.effectivePeriodType}, effective_start_time=#{e.effectiveStartTime},",
            "effective_end_time=#{e.effectiveEndTime}, remark=#{e.remark}",
            "where id=#{e.id}"
    })
    int edit(@Param("e") FciPropEntity entity);


    /**
     * 根据id获取组件属性记录
     *
     * @param id 组件属性id
     * @return 组件属性记录
     */
    @Select("select * from ag_cms_fci_prop where id=#{id}")
    FciPropEntity getById(@Param("id") Long id);


    /**
     * 根据id删除组件属性记录
     *
     * @param id 组件属性id
     * @return 删除条数
     */
    @Update("update ag_cms_fci_prop set deleted=1 where id=#{id}")
    int deleteById(@Param("id") Long id);


    /**
     * 根据组件id获取组件属性记录
     *
     * @param fciId 组件id
     * @return 组件属性记录
     */
    @Select("select * from ag_cms_fci_prop where fci_id=#{fciId} and deleted=1")
    List<FciPropEntity> findByFciId(@Param("fciId") Long fciId);


    /**
     * 根据组件id获取当前有效的组件属性记录
     *
     * @param fciId 组件id
     * @return 组件属性记录
     */
    List<FciPropEntity> findValidByFciId(@Param("fciId") Long fciId);


    /**
     * 根据组件id和属性key获取组件属性记录
     *
     * @param fciId   组件id
     * @param propKey 属性key
     * @return 组件属性记录
     */
    @Select("select * from ag_cms_fci_prop where deleted=1 and fci_id=#{fciId} and prop_key=#{propKey}")
    List<FciPropEntity> findByPropKey(@Param("fciId") Long fciId, @Param("propKey") String propKey);


    /**
     * 关闭组件属性
     *
     * @param fciId   组件实例ID
     * @param propKey 组件属性key
     * @return 是否关闭成功
     */
    @Update("update ag_cms_fci_prop set switch_flag=0 where fci_id=#{fciId} and prop_key=#{propKey}")
    Boolean closePropKey(@Param("fciId") Long fciId, @Param("propKey") String propKey);
}
