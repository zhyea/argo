package com.zhyea.argo.except;

import org.chobit.commons.contract.CodeDescEnum;

/**
 * Argo服务端异常
 *
 * @author robin
 */
public class ArgoServerException extends RuntimeException {


	private static final long serialVersionUID = 7773203247174587636L;


	private final int code;

	public ArgoServerException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public ArgoServerException(int code, String msg, Throwable t) {
		super(msg, t);
		this.code = code;
	}


	public ArgoServerException(CodeDescEnum responseCode) {
		this(responseCode.getCode(), responseCode.getDesc());
	}


	public int getCode() {
		return code;
	}
}
