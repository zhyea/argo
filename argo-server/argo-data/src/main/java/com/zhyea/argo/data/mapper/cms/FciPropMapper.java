package com.zhyea.argo.data.mapper.cms;

import com.zhyea.argo.data.dto.FciPropDto;
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
	void add(@Param("e") FciPropEntity entity);


	/**
	 * 修改组件属性记录
	 *
	 * @param entity 组件属性数据
	 * @return 修改条数
	 */
	int edit(@Param("e") FciPropEntity entity);


	/**
	 * 根据id获取组件属性记录
	 *
	 * @param id 组件属性id
	 * @return 组件属性记录
	 */
	FciPropDto getById(@Param("id") Long id);


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
	 * @param fciId   组件id
	 * @param keyword 关键字
	 * @return 组件属性记录
	 */
	List<FciPropEntity> findInPage(@Param("fciId") Long fciId, @Param("keyword") String keyword);


    /**
     * 根据组件id获取当前生效中的组件属性记录
     *
     * @param fciId 组件id
     * @return 组件属性记录
     */
    List<FciPropEntity> findEffectiveByFciId(@Param("fciId") Long fciId);


    /**
     * 根据组件id获取当前有效的组件属性记录
     *
     * @param fciId 组件id
     * @return 组件属性记录
     */
    List<FciPropEntity> findInEffectAndPendingByFciId(@Param("fciId") Long fciId);


	/**
	 * 根据组件id和属性key获取组件属性记录
	 *
	 * @param fciId   组件id
	 * @param propKey 属性key
	 * @return 组件属性记录
	 */
	List<FciPropEntity> findByPropKey(@Param("fciId") Long fciId, @Param("propKey") String propKey);


	/**
	 * 关闭组件属性
	 *
	 * @param id         组件实例属性ID
	 * @param switchFlag 开关标记
	 * @return 是否关闭成功
	 */
	Boolean switchProp(@Param("id") Long id, @Param("switchFlag") Integer switchFlag);
}
