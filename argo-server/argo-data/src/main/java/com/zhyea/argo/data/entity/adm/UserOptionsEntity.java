package com.zhyea.argo.data.entity.adm;

import com.zhyea.argo.data.entity.BaseEntity;
import lombok.Data;

/**
 * 用户配置
 *
 * @author robin
 * @since 2025/6/24 7:51
 */
@Data
public class UserOptionsEntity extends BaseEntity {


	/**
	 * 用户名
	 */
	private String username;


	/**
	 * 配置项key
	 */
	private String optionKey;


	/**
	 * 配置项值
	 */
	private String optionVal;


}
