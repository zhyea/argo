package com.zhyea.argo.config.response;

import lombok.Data;
import org.chobit.commons.model.response.Result;

/**
 * 统一返回结果包装
 *
 * @author robin
 * @since 2025/4/26 17:32
 */
@Data
public class ResultWrapper<T> extends Result<T> {


	public ResultWrapper(int code, T data) {
		super(code, data);
	}


	public ResultWrapper(T data) {
		super(data);
	}


	public ResultWrapper(int code) {
		super(code);
	}


	private String authToken;

}
