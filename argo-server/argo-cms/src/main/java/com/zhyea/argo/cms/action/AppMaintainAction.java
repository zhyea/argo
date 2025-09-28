package com.zhyea.argo.cms.action;

import com.zhyea.argo.biz.service.AppService;
import com.zhyea.argo.biz.service.FciService;
import com.zhyea.argo.biz.service.FcmService;
import com.zhyea.argo.biz.service.PageService;
import com.zhyea.argo.except.ArgoServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.zhyea.argo.constants.ResponseCode.*;

/**
 * 应用维护Action
 *
 * @author robin
 * @since 2025/9/28 21:21
 */
@Slf4j
@Component
public class AppMaintainAction {


	private final AppService appService;
	private final PageService pageService;
	private final FcmService fcmService;
	private final FciService fciService;


	@Autowired
	public AppMaintainAction(AppService appService,
	                         PageService pageService,
	                         FcmService fcmService,
	                         FciService fciService) {
		this.appService = appService;
		this.pageService = pageService;
		this.fcmService = fcmService;
		this.fciService = fciService;
	}


	/**
	 * 删除应用
	 *
	 * @param appId 应用id
	 * @return 是否删除成功
	 */
	public boolean delete(Long appId) {

		long fciCount = fciService.countByAppId(appId);
		if (fciCount > 0) {
			throw new ArgoServerException(APP_CANNOT_BE_DELETED_WITH_FCI);
		}

		long fcmCount = fcmService.countByAppId(appId);
		if (fcmCount > 0) {
			throw new ArgoServerException(APP_CANNOT_BE_DELETED_WITH_FCM);
		}

		long pageCount = pageService.countByAppId(appId);
		if (pageCount > 0) {
			throw new ArgoServerException(APP_CANNOT_BE_DELETED_WITH_PAGE);
		}

		return appService.delete(appId);
	}

}
