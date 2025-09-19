package com.zhyea.argo.cms.action;

import com.zhyea.argo.model.item.FciItem;
import com.zhyea.argo.model.item.FciPropItem;
import com.zhyea.argo.model.item.PageItem;
import com.zhyea.argo.biz.service.FciPropService;
import com.zhyea.argo.biz.service.FciService;
import com.zhyea.argo.biz.service.PageService;
import com.zhyea.argo.constants.enums.DataBindFlagEnum;
import com.zhyea.argo.tools.ParallelKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取页面组件信息相关操作
 *
 * @author zhangrui
 * @since 2025/9/17
 */
@Slf4j
@Component
public class FciGetAction {


	private final FciService fciService;
	private final FciPropService fciPropService;
	private final PageService pageService;


	@Autowired
	public FciGetAction(FciService fciService, FciPropService fciPropService, PageService pageService) {
		this.fciService = fciService;
		this.fciPropService = fciPropService;
		this.pageService = pageService;
	}


	public FciItem getByFciId(Long id) {
		List<Object> result = ParallelKit.executeIgnoreErrors(() -> fciService.getById(id),
				() -> fciPropService.findInEffectAndPendingByFciId(id),
				() -> pageService.findFciUsage(id));

		boolean existsInheritedProps = false;
		if (null != result.get(1)) {
			List<FciPropItem> fciProps = (List<FciPropItem>) result.get(1);
			existsInheritedProps = fciProps.stream().anyMatch(prop -> DataBindFlagEnum.INHERIT.is(prop.getDataBindFlag()));
		}

		List<PageItem> pageList = null;
		if (null != result.get(2)) {
			pageList = (List<PageItem>) result.get(2);
		}

		FciItem fciItem = null;
		if (null != result.get(0)) {
			fciItem = (FciItem) result.getFirst();
			fciItem.setExistsInheritedProps(existsInheritedProps);
			fciItem.setPageList(pageList);
		}
		return fciItem;
	}
}
