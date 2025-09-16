package com.zhyea.argo.data.mapper.cms;

import com.zhyea.argo.data.entity.cms.FciRequestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 组件实例请求信息Mapper
 *
 * @author robin
 * @since 2025/9/8 23:07
 */
@Mapper
public interface FciRequestMapper {


	/**
	 * 添加
	 *
	 * @param entity 实体
	 * @return 添加结果
	 */
	Long add(@Param("e") FciRequestEntity entity);


	/**
	 * 修改
	 *
	 * @param entity 实体
	 * @return 修改结果
	 */
	boolean modify(@Param("e") FciRequestEntity entity);

	/**
	 * 根据绑定类型和绑定ID获取记录
	 *
	 * @param bindType 绑定类型
	 * @param belongId 绑定ID
	 * @return 实体
	 */
	FciRequestEntity getByBindId(@Param("bindType") int bindType, @Param("belongId") Long belongId);


	/**
	 * 根据绑定类型和绑定ID删除记录
	 *
	 * @param bindType 绑定类型
	 * @param belongId 绑定ID
	 * @return 删除结果
	 */
	boolean deleteByBindId(@Param("bindType") int bindType, @Param("belongId") Long belongId);

}
