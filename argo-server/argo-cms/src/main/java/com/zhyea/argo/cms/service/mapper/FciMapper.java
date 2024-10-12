package com.zhyea.argo.cms.service.mapper;

import com.zhyea.argo.cms.model.entity.FciEntity;
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
	@Insert({
			"insert into ag_cms_fci (page_id, fcm_id, data_url, switch_flag, ",
			"effective_period_type, effective_start_time, effective_end_time, remark)",
			"values",
			"(#{e.pageId}, #{e.fcmId}, #{e.dataUrl}, #{e.switchFlag}, ",
			"#{e.effectivePeriodType}, #{e.effectiveStartTime}, #{e.effectiveEndTime}, #{e.remark})"
	})
	@Options(useGeneratedKeys = true)
	void add(@Param("e") FciEntity entity);


	/**
	 * 修改组件实例
	 *
	 * @param entity 组件数据
	 * @return 影响行数
	 */
	@Update({
			"update ag_cms_fci set",
			"page_id=#{e.pageId}, fcm_id=#{e.fcmId}, data_url=#{e.dataUrl}, switch_flag=#{e.switchFLag}, ",
			"effective_period_type=#{e.effectivePeriodType},",
			"effective_start_time=#{e.effectiveStartTime}, effective_end_time=#{e.effectiveEndTime}, remark=#{e.remark}",
			"where id=#{e.id}"
	})
	int modify(@Param("e") FciEntity entity);


	/**
	 * 根据id获取组件实例
	 *
	 * @param id 组件实例id
	 * @return 组件实例
	 */
	@Select("select * from ag_cms_fci where id=#{id}")
	FciEntity getById(@Param("id") int id);


	/**
	 * 根据id删除组件实例
	 *
	 * @param id 组件实例id
	 * @return 影响行数
	 */
	@Update("update ag_cms_fci set deleted=1 where id=#{id}")
	int deleteById(@Param("id") int id);


	/**
	 * 根据pageId获取组件实例
	 *
	 * @param pageId 页面id
	 * @return 组件实例列表
	 */
	@Select("select * from ag_cms_fci where page_id=#{pageId} and deleted=0")
	List<FciEntity> findByPageId(@Param("pageId") Long pageId);

}
