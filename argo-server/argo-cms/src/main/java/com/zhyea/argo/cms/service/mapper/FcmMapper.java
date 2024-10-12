package com.zhyea.argo.cms.service.mapper;

import com.zhyea.argo.cms.model.entity.FcmEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 组件模型 mapper
 *
 * @author robin
 */
@Mapper
public interface FcmMapper {


	/**
	 * 新增组件模型
	 *
	 * @param entity 组件模型数据
	 */
	@Insert({
			"insert into ag_cms_fcm (scope, app_id, name, icon, type, props, data_bind_flag, remark)",
			"values",
			"(#{e.scope}, #{e.appId}, #{e.name}, #{e.icon}, #{e.type}, #{e.props}, #{e.dataBindFlag}, #{e.remark})"
	})
	@Options(useGeneratedKeys = true)
	void add(@Param("e") FcmEntity entity);


	/**
	 * 修改组件模型
	 *
	 * @param entity 组件模型数据
	 * @return 影响行数
	 */
	@Update({
			"update ag_cms_fcm set",
			"scope=#{e.scope}, app_id=#{e.appId}, name=#{e.name}, icon=#{e.icon}, type=#{e.type}, ",
			"props=#{e.props}, data_bind_flag=#{e.dataBindFlag}, remark=#{e.remark}",
			"where id=#{e.id}"
	})
	int modify(@Param("e") FcmEntity entity);


	/**
	 * 根据id获取组件模型
	 *
	 * @param id 组件模型id
	 * @return 组件模型记录
	 */
	@Select("select * from ag_cms_fcm where id=#{id}")
	FcmEntity getById(@Param("id") int id);


	/**
	 * 根据id删除组件模型
	 *
	 * @param id 组件模型id
	 * @return 影响行数
	 */
	@Update("update ag_cms_fcm set deleted=1 where id=#{id}")
	int deleteById(@Param("id") int id);


	/**
	 * 根据应用id获取组件模型
	 *
	 * @param appId 应用id
	 * @return 组件模型列表
	 */
	@Select("select * from ag_cms_fcm where app_id=#{appId}")
	List<FcmEntity> findByAppId(@Param("appId") String appId);


	/**
	 * 获取所有组件模型
	 *
	 * @return 组件模型列表
	 */
	@Select("select * from ag_cms_fcm")
	List<FcmEntity> findAll();


}
