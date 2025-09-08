package com.zhyea.argo.data.mapper.cms;

import com.zhyea.argo.data.entity.cms.FciRequestEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 组件实例请求信息Mapper
 *
 * @author robin
 * @since 2025/9/8 23:07
 */
@Mapper
public interface FciRequestMapper {


	void add(@Param("e") FciRequestEntity entity);


	void modify(@Param("e") FciRequestEntity entity);


	@Select("select * from ag_cms_fci_request where id=#{id}")
	FciRequestEntity getById(@Param("id") Long id);


	@Delete("delete from ag_cms_fci_request where id=#{id}")
	boolean deleteById(@Param("id") Long id);

}
