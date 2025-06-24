package com.zhyea.argo.data.mapper.adm;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户选项
 *
 * @author robin
 * @since 2025/6/24 7:54
 */
@Mapper
public interface UserOptionsMapper {


	/**
	 * 设置用户配置
	 *
	 * @param username  用户名
	 * @param optionKey 配置key
	 * @param optionVal 配置值
	 */
	void set(@Param("username") String username, @Param("optionKey") String optionKey, @Param("optionVal") String optionVal);


	/**
	 * 获取用户配置
	 *
	 * @param username  用户名
	 * @param optionKey 配置key
	 * @return 配置值
	 */
	@Select("select option_val from ag_adm_user_options where username=#{username} and option_key=#{optionKey}")
	String get(@Param("username") String username, @Param("optionKey") String optionKey);

}
