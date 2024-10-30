package com.zhyea.argo.data.mapper.cms;

import com.zhyea.argo.data.dto.FcmDto;
import com.zhyea.argo.data.entity.cms.FcmEntity;
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
			"insert into ag_cms_fcm (scope, app_code, name, icon, type, props, data_bind_flag, uniq_code, remark)",
			"values",
			"(#{e.scope}, ",
			"#{e.appCode, typeHandler=com.zhyea.argo.data.type.NullTypeHandler}, ",
			"#{e.name}, ",
			"#{e.icon, typeHandler=com.zhyea.argo.data.type.NullTypeHandler}, ",
			"#{e.type}, ",
			"#{e.props, typeHandler=com.zhyea.argo.data.type.NullTypeHandler}, ",
			"#{e.dataBindFlag}, ",
			"#{e.uniqCode}, ",
			"#{e.remark, typeHandler=com.zhyea.argo.data.type.NullTypeHandler})"
	})
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void add(@Param("e") FcmEntity entity);


	/**
	 * 修改组件模型
	 *
	 * @param entity 组件模型数据
	 * @return 影响行数
	 */
	@Update({
			"update ag_cms_fcm set",
			"scope=#{e.scope}, ",
			"app_code=#{e.appCode, typeHandler=com.zhyea.argo.data.type.NullTypeHandler}, ",
			"name=#{e.name}, ",
			"icon=#{e.icon, typeHandler=com.zhyea.argo.data.type.NullTypeHandler}, ",
			"type=#{e.type}, ",
			"props=#{e.props, typeHandler=com.zhyea.argo.data.type.NullTypeHandler}, ",
			"data_bind_flag=#{e.dataBindFlag}, ",
			"remark=#{e.remark, typeHandler=com.zhyea.argo.data.type.NullTypeHandler}",
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
	FcmEntity getById(@Param("id") Long id);


	/**
	 * 根据id删除组件模型
	 *
	 * @param id 组件模型id
	 * @return 影响行数
	 */
	@Update("update ag_cms_fcm set deleted=1 where id=#{id}")
	int deleteById(@Param("id") Long id);


	/**
	 * 根据应用id获取组件模型
	 *
	 * @param appCode 应用id
	 * @return 组件模型列表
	 */
	@Select("select * from ag_cms_fcm where app_code=#{appCode}")
	List<FcmEntity> findByAppId(@Param("appCode") String appCode);


	/**
	 * 获取所有组件模型
	 *
	 * @return 组件模型列表
	 */
	List<FcmDto> query(@Param("appCode") String appCode, @Param("keyword") String keyword);


	/**
	 * 根据唯一编码获取组件模型
	 *
	 * @param uniqCode 唯一编码
	 * @return 组件模型
	 */
	FcmEntity getByUniqCode(@Param("uniqCode") String uniqCode, @Param("id") Long id);
}
