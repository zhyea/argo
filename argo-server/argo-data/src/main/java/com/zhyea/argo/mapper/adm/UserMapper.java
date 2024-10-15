package com.zhyea.argo.mapper.adm;

import com.zhyea.argo.entity.adm.UserEntity;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;


/**
 * 用户信息表Mapper
 *
 * @author robin
 */
@Mapper
public interface UserMapper {


	/**
	 * 根据用户名和密码获取用户信息
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户信息集合
	 */
	@Select({"select * from ag_adm_user where username=#{username} and password=#{password}"})
	UserEntity getByUserAndPassword(@Param("username") String username,
	                                @Param("password") String password);


	/**
	 * 新增用户记录
	 *
	 * @param entity 用户信息
	 */
	@Insert({
			"insert into ag_adm_user (username, password, nickname)",
			"values",
			"(#{item.username}, #{item.password}, #{item.nickname})",
	})
	@Options(useGeneratedKeys = true)
	void add(@Param("item") UserEntity entity);


	/**
	 * 重置上次登录时间
	 *
	 * @param username 用户名
	 * @param time     上次登录的时间
	 */
	@Update({
			"update ag_adm_user set last_login_time=#{time}",
			"where username=#{username}",
			"limit 1"
	})
	void resetLoginTime(@Param("username") String username, @Param("time") LocalDateTime time);
}
