package com.zhyea.argo.cms.service.mapper;

import com.zhyea.argo.cms.model.entity.AppPageEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 应用页面 mapper
 *
 * @author robin
 */
@Mapper
public interface AppPageMapper {


	/**
	 * 新增应用页面记录
	 *
	 * @param entity 应用页面数据
	 */
	@Select({
			"insert into ag_cms_app_page (app_id, page_code, page_name, remark)",
			"values",
			"(#{e.appId}, #{e.pageCode}, #{e.pageName}, #{e.remark})"
	})
	@Options(useGeneratedKeys = true)
	void add(@Param("e") AppPageEntity entity);


	/**
	 * 修改应用页面记录
	 *
	 * @param entity 应用页面数据
	 * @return 影响行数
	 */
	@Update({
			"update ag_cms_app_page set",
			"app_id=#{e.appId}, page_code=#{e.pageCode}, page_name=#{e.pageName}, remark=#{e.remark}",
			"where id=#{e.id}"
	})
	int modify(@Param("e") AppPageEntity entity);


	/**
	 * 根据id获取应用页面记录
	 *
	 * @param id 应用页面id
	 * @return 应用页面记录
	 */
	@Select("select * from ag_cms_app_page where id=#{id}")
	AppPageEntity getById(@Param("id") int id);


	/**
	 * 根据id删除应用页面记录
	 *
	 * @param id 应用页面id
	 * @return 影响行数
	 */
	@Update("update ag_cms_app_page set deleted=1 where id=#{id}")
	int deleteById(@Param("id") int id);


	/**
	 * 根据应用id获取应用页面记录
	 *
	 * @param appId 应用id
	 * @return 应用页面记录集合
	 */
	@Select("select * from ag_cms_app_page where app_id=#{appId}")
	List<AppPageEntity> getByAppId(@Param("appId") int appId);


}
