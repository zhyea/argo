package com.zhyea.argo.data.mapper.adm;

import com.zhyea.argo.data.entity.adm.UserEntity;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;


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

	/**
	 * 获取用户列表
	 *
	 * @param keyword 关键字（用户名或昵称）
	 * @param offset  偏移量
	 * @param size    分页大小
	 * @return 用户列表
	 */
	List<UserEntity> findList(@Param("keyword") String keyword, @Param("offset") int offset, @Param("size") int size);

	/**
	 * 获取用户总数
	 *
	 * @param keyword 关键字（用户名或昵称）
	 * @return 用户总数
	 */
	long count(@Param("keyword") String keyword);

	/**
	 * 根据ID获取用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	@Select({"select * from ag_adm_user where id=#{id}"})
	UserEntity getById(@Param("id") Long id);

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param username 用户名
	 * @return 用户信息
	 */
	@Select({"select * from ag_adm_user where username=#{username}"})
	UserEntity getByUsername(@Param("username") String username);

	/**
	 * 更新用户信息
	 *
	 * @param entity 用户信息
	 */
	@Update({
			"update ag_adm_user set nickname=#{item.nickname}",
			"where id=#{item.id}"
	})
	void update(@Param("item") UserEntity entity);

	/**
	 * 删除用户
	 *
	 * @param id 用户ID
	 */
	@Delete({"delete from ag_adm_user where id=#{id}"})
	void delete(@Param("id") Long id);

	/**
	 * 修改密码
	 *
	 * @param id       用户ID
	 * @param password 新密码
	 */
	@Update({
			"update ag_adm_user set password=#{password} where id=#{id}"
	})
	void updatePassword(@Param("id") Long id, @Param("password") String password);
}
