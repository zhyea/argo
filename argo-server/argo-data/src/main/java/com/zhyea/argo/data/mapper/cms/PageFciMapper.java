package com.zhyea.argo.data.mapper.cms;

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
}
