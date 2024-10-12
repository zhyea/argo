package com.zhyea.argo.cms.service.mapper;

import com.zhyea.argo.cms.model.entity.FciEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组件实例 mapper
 *
 * @author robin
 */
@Mapper
public interface FciMapper {


	/**
	 * 新增组件实例
	 *
	 * @param entity 组件数据
	 */
	void add(@Param("e") FciEntity entity);


	/**
	 * 修改组件实例
	 *
	 * @param entity 组件数据
	 */
	void modify(@Param("e") FciEntity entity);


	/**
	 * 根据id获取组件实例
	 *
	 * @param id 组件实例id
	 * @return 组件实例
	 */
	FciEntity getById(@Param("id") int id);


	/**
	 * 根据id删除组件实例
	 *
	 * @param id 组件实例id
	 */
	void deleteById(@Param("id") int id);


	/**
	 * 根据pageId获取组件实例
	 *
	 * @param pageId 页面id
	 * @return 组件实例列表
	 */
	List<FciEntity> findByPageId(@Param("pageId") Long pageId);

}
