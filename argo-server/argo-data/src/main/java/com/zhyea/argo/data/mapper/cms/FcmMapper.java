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
			"app_id=#{e.appId, typeHandler=com.zhyea.argo.data.type.NullBigIntHandler}, ",
			"name=#{e.name}, ",
			"icon=#{e.icon, typeHandler=com.zhyea.argo.data.type.NullStringHandler}, ",
			"type=#{e.type}, ",
			"props=#{e.props, typeHandler=com.zhyea.argo.data.type.NullStringHandler}, ",
			"data_bind_flag=#{e.dataBindFlag}, ",
			"remark=#{e.remark, typeHandler=com.zhyea.argo.data.type.NullStringHandler}",
			"where id=#{e.id}"
	})
	int modify(@Param("e") FcmEntity entity);


	/**
	 * 根据id获取组件模型
	 *
	 * @param id 组件模型id
	 * @return 组件模型记录
	 */
	FcmDto getById(@Param("id") Long id);


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
	 * @param appId 应用id
	 * @return 组件模型列表
	 */
	@Select("select * from ag_cms_fcm where app_id=#{appId}")
	List<FcmEntity> findByAppId(@Param("appId") Long appId);


	/**
	 * 获取所有组件模型
	 *
	 * @return 组件模型列表
	 */
	List<FcmDto> query(@Param("appId") Long appId, @Param("keyword") String keyword);


	/**
	 * 根据唯一编码获取组件模型
	 *
	 * @param uniqCode 唯一编码
	 * @return 组件模型
	 */
	FcmEntity getByUniqCode(@Param("uniqCode") String uniqCode, @Param("id") Long id);
}
