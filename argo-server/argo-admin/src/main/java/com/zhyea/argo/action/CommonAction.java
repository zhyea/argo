package com.zhyea.argo.action;

import com.zhyea.argo.constants.enums.*;
import com.zhyea.argo.tools.EnumMapBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 通用业务处理类
 *
 * @author robin
 */
@Component
public class CommonAction {


	/**
	 * 将枚举转换为Map结构，便于前端使用
	 *
	 * @return 枚举对应的Map结构
	 */
	public Map<String, Map<Integer, String>> enumMap() {
		EnumMapBuilder builder = new EnumMapBuilder();

		builder.add(FcmTypeEnum.class)
				.add(FcScopeEnum.class)
				.add(YesOrNo.class)
				.add(FcmPropTypeEnum.class)
				.add(EffectivePeriodTypeEnum.class);

		return builder.build();
	}


}
