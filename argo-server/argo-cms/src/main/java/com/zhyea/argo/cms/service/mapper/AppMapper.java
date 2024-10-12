package com.zhyea.argo.cms.service.mapper;

import com.zhyea.argo.cms.model.entity.AppEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 应用数据处理
 *
 * @author robin
 */
@Mapper
public interface AppMapper {


	/**
	 * 新增应用记录
	 *
	 * @param app 应用信息
	 */
	@Insert({
			"insert into ag_cms_app (app_name, icon, remark)",
			"values",
			"(#{e.appName}, #{e.icon}, #{e.remark})"
	})
	@Options(useGeneratedKeys = true)
	void add(@Param("e") AppEntity app);


	/**
	 * 修改应用记录
	 *
	 * @param app 应用信息
	 * @return 影响行数
	 */
	@Update({
			"update ag_cms_app set",
			"app_name=#{e.appName}, icon=#{e.icon}, remark=#{e.remark}",
			"where id=#{e.id}"
	})
	int modify(@Param("e") AppEntity app);


	/**
	 * 根据id获取应用记录
	 *
	 * @param id 应用id
	 * @return 应用记录
	 */
	@Select("select * from ag_cms_app where id=#{id}")
	AppEntity getById(@Param("id") int id);


	/**
	 * 根据id删除应用记录
	 *
	 * @param id 应用id
	 * @return 影响行数
	 */
	@Update("update ag_cms_app set deleted=1 where id=#{id}")
	int deleteById(@Param("id") int id);


	/**
	 * 获取所有应用记录
	 *
	 * @return 应用记录列表
	 */
	@Select("select * from ag_cms_app where deleted=#{deleted}")
	List<AppEntity> findAll();


}
