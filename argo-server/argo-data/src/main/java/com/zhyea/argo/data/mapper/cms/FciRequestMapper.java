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


	Long add(@Param("e") FciRequestEntity entity);


	boolean modify(@Param("e") FciRequestEntity entity);


	FciRequestEntity getByBelongId(@Param("bindType") int bindType, @Param("belongId") Long belongId);


	boolean deleteByBelongId(@Param("bindType") int bindType, @Param("belongId") Long belongId);

}
