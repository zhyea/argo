package com.zhyea.argo.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应结果
 *
 * @param <T> 数据类型
 * @author robin
 */
@Data
public class PageResponse<T> implements Serializable {


	private static final long serialVersionUID = 8451488857993571292L;


	/**
	 * 数据列表
	 */
	private List<T> data;

	/**
	 * 总记录数
	 */
	private long total;

	/**
	 * 当前页码
	 */
	private int pageNo;

	/**
	 * 每页记录数
	 */
	private int pageSize;

	/**
	 * 总页数
	 */
	private int totalPage;


	/**
	 * 构造函数
	 *
	 * @param data     数据列表
	 * @param total    总记录数
	 * @param pageNo   当前页码
	 * @param pageSize 每页记录数
	 */
	public PageResponse(List<T> data, long total, int pageNo, int pageSize) {
		this.data = data;
		this.total = total;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalPage = (int) Math.ceil((double) total / pageSize);
	}

}