package com.zhyea.argo.except;

import com.zhyea.argo.constants.ResponseCode;
import lombok.Getter;
import org.chobit.spring.autoconfigure.rw.exception.RwException;

/**
 * Argo服务端异常
 *
 * @author robin
 */
@Getter
public class ArgoAdminException extends RwException {


	private static final long serialVersionUID = 7773203247174587636L;


	public ArgoAdminException(ResponseCode code) {
		super(code.code, code.msg);
	}

	public ArgoAdminException(ResponseCode code, Throwable t) {
		super(code.code, code.msg, t);
	}


}
