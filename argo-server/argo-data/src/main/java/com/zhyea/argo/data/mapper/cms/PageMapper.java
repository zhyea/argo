package com.zhyea.argo.data.mapper.cms;

import com.zhyea.argo.data.entity.cms.PageEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 应用页面 mapper
 *
 * @author robin
 */
@Mapper
public interface PageMapper {


	/**
	 * 新增应用页面记录
	 *
	 * @param page 应用页面数据
	 */
	@Insert({
			"insert into ag_cms_app_page (app_id, page_code, page_name, remark)",
			"values",
			"(#{e.appId}, #{e.pageCode}, #{e.pageName},",
			"#{e.remark, typeHandler=com.zhyea.argo.data.type.NullStringHandler})",
			""
	})
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void add(@Param("e") PageEntity page);


	/**
	 * 修改应用页面记录
	 *
	 * @param entity 应用页面数据
	 * @return 影响行数
	 */
	@Update({
			"update ag_cms_app_page set",
			"app_id=#{e.appId}, page_code=#{e.pageCode}, page_name=#{e.pageName}, remark=#{e.remark}",
			"where id=#{e.id}"
	})
	int modify(@Param("e") PageEntity entity);


	/**
	 * 根据id获取应用页面记录
	 *
	 * @param id 应用页面id
	 * @return 应用页面记录
	 */
	@Select("select * from ag_cms_app_page where id=#{id}")
	PageEntity getById(@Param("id") Long id);


	/**
	 * 根据id删除应用页面记录
	 *
	 * @param id 应用页面id
	 * @return 影响行数
	 */
	@Delete("delete from ag_cms_app_page where id=#{id}")
	int deleteById(@Param("id") Long id);


	/**
	 * 根据应用id获取应用页面记录
	 *
	 * @param appId 应用id
	 * @return 应用页面记录集合
	 */
	List<PageEntity> query(@Param("appId") Long appId, @Param("keyword") String keyword);


	/**
	 * 根据页面code统计应用页面记录
	 *
	 * @param pageCode 页面code
	 * @return 统计结果
	 */
	@Select("select count(id) from ag_cms_app_page where page_code=#{pageCode} and deleted=0")
	long countByPageCode(@Param("pageCode") String pageCode);


	/**
	 * 根据页面code获取应用页面记录
	 *
	 * @param pageCode 页面code
	 * @return 应用页面记录
	 */
	@Select("select * from ag_cms_app_page where page_code= #{pageCode} and deleted=0")
	PageEntity getByPageCode(@Param("pageCode") String pageCode);

}
