package com.zhyea.argo.tools;

import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.except.ArgoServerException;

import static org.chobit.commons.utils.StrKit.isBlank;

/**
 *
 *
 * @author robin
 * @since 2025/9/4 22:56
 */
public final class Args {


	public static void check(boolean exp, ResponseCode code) {
		if (!exp) {
			throw new ArgoServerException(code);
		}
	}


	public static <T> void checkNotNull(T t, ResponseCode code) {
		if (null == t) {
			throw new ArgoServerException(code);
		}
	}


	public static void checkNotBlank(String arg, ResponseCode code) {
		if (isBlank(arg)) {
			throw new ArgoServerException(code);
		}
	}


}
