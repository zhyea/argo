package com.zhyea.argo.data.mapper.cms;

import com.zhyea.argo.data.entity.cms.FciEntity;
import com.zhyea.argo.data.entity.cms.PageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 页面组件实例关联
 *
 * @author zhyea
 */
@Mapper
public interface PageFciMapper {


	/**
	 * 批量添加页面和组件实例关联关系
	 *
	 * @param pageId 页面id
	 * @param fciIds 组件实例ID集合
	 * @return 添加成功数量
	 */
	Integer batchAdd(@Param("pageId") Long pageId, @Param("fciIdList") List<Long> fciIds);


	/**
	 * 查询使用组件实例的页面
	 *
	 * @param fciId 组件实例id
	 * @return 使用组件实例的页面
	 */
	List<PageEntity> findFciUsage(@Param("fciId") Long fciId);


	/**
	 * 查询可用的组件实例
	 * <p>
	 * 结果需要去重
	 *
	 * @param appId 应用id
	 * @return 可用的组件实例
	 */
	List<FciEntity> findAvailableFci(@Param("appId") Long appId, @Param("pageId") Long pageId);


	/**
	 * 根据页面id查询页面使用的组件实例
	 *
	 * @param pageId 页面id
	 * @return 页面组件实例
	 */
	List<FciEntity> findByPageId(@Param("pageId") Long pageId);
}
