package com.zhyea.argo.data.mapper.cms;

import com.zhyea.argo.data.entity.cms.PageFciEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


/**
 * 页面组件实例关联
 *
 * @author zhyea
 */
@Mapper
public interface PageFciMapper {


	@Insert({
			"insert into ag_cms_page_fci (page_id, fci_id)",
			"values",
			"	(#{pageId}, #{fciId})",
	})
	int add(PageFciEntity pageFciEntity);


}
