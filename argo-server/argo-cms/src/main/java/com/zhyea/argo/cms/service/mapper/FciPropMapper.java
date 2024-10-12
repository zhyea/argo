package com.zhyea.argo.cms.service.mapper;

import com.zhyea.argo.cms.model.entity.FciPropEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 组件属性 mapper
 *
 * @author robin
 */
@Mapper
public interface FciPropMapper {


	/**
	 * 新增组件属性记录
	 *
	 * @param entity 组件属性数据
	 */
	@Insert({
			"insert into ag_cms_fci_prop (fci_id, prop_key, prop_value, prop_value_selector",
			"switch_flag, effective_period_type, effective_start_time, effective_end_time, remark)",
			"values",
			"(#{e.fciId}, #{e.propKey}, #{e.propValue}, #{e.propValueSelector},",
			"#{e.switchFlag}, #{e.effectivePeriodType}, #{e.effectiveStartTime}, #{e.effectiveEndTime}, #{e.remark})"
	})
	void add(@Param("e") FciPropEntity entity);


	/**
	 * 修改组件属性记录
	 *
	 * @param entity 组件属性数据
	 * @return 修改条数
	 */
	@Update({
			"update ag_cms_fci_prop set",
			"fci_id=#{e.fciId}, prop_key=#{e.propKey}, prop_value=#{e.propValue}, prop_value_selector=#{e.propValueSelector},",
			"effective_period_type=#{e.effectivePeriodType}, effective_start_time=#{e.effectiveStartTime},",
			"effective_end_time=#{e.effectiveEndTime}, remark=#{e.remark}",
			"where id=#{e.id}"
	})
	int modify(@Param("e") FciPropEntity entity);


	/**
	 * 根据id获取组件属性记录
	 *
	 * @param id 组件属性id
	 * @return 组件属性记录
	 */
	@Select("select * from ag_cms_fci_prop where id=#{id}")
	FciPropEntity getById(@Param("id") int id);


	/**
	 * 根据id删除组件属性记录
	 *
	 * @param id 组件属性id
	 * @return 删除条数
	 */
	@Update("update ag_cms_fci_prop set deleted=1 where id=#{id}")
	int deleteById(@Param("id") int id);


	/**
	 * 根据组件id获取组件属性记录
	 *
	 * @param fciId 组件id
	 * @return 组件属性记录
	 */
	@Select("select * from ag_cms_fci_prop where fci_id=#{fciId} and deleted=1")
	List<FciPropEntity> findByFciId(@Param("fciId") int fciId);


}
