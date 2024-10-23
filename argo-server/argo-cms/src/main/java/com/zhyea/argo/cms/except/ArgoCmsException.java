package com.zhyea.argo.cms.except;

import com.zhyea.argo.constants.ResponseCode;
import lombok.Getter;
import org.chobit.commons.contract.CodeDescEnum;
import org.chobit.spring.autoconfigure.rw.exception.RwException;

/**
 * Argo服务端异常
 *
 * @author robin
 */
@Getter
public class ArgoCmsException extends RwException {


	private static final long serialVersionUID = 7773203247174587636L;


	public ArgoCmsException(CodeDescEnum code) {
		super(code.getCode(), code.getDesc());
	}

	public ArgoCmsException(ResponseCode code, Throwable t) {
		super(code.code, code.msg, t);
	}


}
