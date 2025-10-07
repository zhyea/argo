package com.zhyea.argo.model.request;

import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * 用户列表查询请求
 *
 * @author robin
 */
@Data
public class UserListQueryRequest implements Serializable {


	private static final long serialVersionUID = 6823475689012345678L;


	/**
	 * 搜索关键字
	 */
	private String keyword;

	/**
	 * 页码
	 */
	@NotNull(message = "页码不可为空")
	@Min(value = 1, message = "页码必须大于等于1")
	private Integer pageNo;

	/**
	 * 每页大小
	 */
	@NotNull(message = "每页大小不可为空")
	@Min(value = 1, message = "每页大小必须大于等于1")
	private Integer pageSize;

}