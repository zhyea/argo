package com.zhyea.argo.cms;

import com.zhyea.argo.cms.model.item.FcmItem;
import com.zhyea.argo.constants.enums.FcmTypeEnum;
import org.chobit.commons.utils.JsonKit;

public class MyApp {

	public static void main(String[] args) {
		FcmItem item = new FcmItem();
		item.setType(FcmTypeEnum.IMAGE.getCode());
		System.out.println(JsonKit.toJson(item));
	}


}
