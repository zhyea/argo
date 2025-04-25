package com.zhyea.argo.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * base entity
 *
 * @author robin
 */
@Data
public abstract class BaseEntity {


	/**
	 * 记录ID
	 */
	private Long id;


	/**
	 * 操作人code
	 */
	private String operatorCode;


	/**
	 * 删除标记
	 */
	private Integer deleted;


	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;


	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;


}
