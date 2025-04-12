package com.zhyea.argo.data.mapper.cms;

import com.zhyea.argo.data.dto.FciDto;
import com.zhyea.argo.data.entity.cms.FciEntity;
import org.apache.ibatis.annotations.*;

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
	 * @return 影响行数
	 */
	int modify(@Param("e") FciEntity entity);


	/**
	 * 根据id获取组件实例
	 *
	 * @param id 组件实例id
	 * @return 组件实例
	 */
	@Select({"select fci.*, fcm.data_bind_flag from ag_cms_fci fci ",
			"left join ag_cms_fcm fcm on fci.fcm_id=fcm.id ",
			"where id=#{id}"})
	FciEntity getById(@Param("id") Long id);


	/**
	 * 根据id删除组件实例
	 *
	 * @param id 组件实例id
	 * @return 影响行数
	 */
	@Update("update ag_cms_fci set deleted=1 where id=#{id}")
	int deleteById(@Param("id") Long id);


	/**
	 * 根据pageId获取组件实例
	 *
	 * @param pageCode 页面code
	 * @return 组件实例列表
	 */
	List<FciDto> findByPageCode(@Param("pageId") String pageCode);


	/**
	 * 根据应用id和关键字查询组件实例
	 *
	 * @param appId   应用id
	 * @param keyword 关键字
	 * @return 组件实例列表
	 */
	List<FciDto> findByAppId(@Param("appId") Long appId, @Param("keyword") String keyword);


	/**
	 * 根据组件模型id查询组件实例数量
	 *
	 * @param fcmId 组件模型id
	 * @return 组件实例数量
	 */
	@Select("select count(id) from ag_cms_fci where deleted=0 and fcm_id=#{fcmId}")
	int countByFcmId(@Param("fcmId") Long fcmId);

}
