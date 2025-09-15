package com.zhyea.argo.tools;

import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.except.ArgoServerException;

import java.util.Objects;

import static org.chobit.commons.utils.StrKit.isBlank;

/**
 * 参数校验
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

	/**
	 * 校验参数是否为空，为空则抛出空指针异常
	 */
	public static void checkEquals(String str,
	                               String other,
	                               ResponseCode responseCode) {
		if (!Objects.equals(str, other)) {
			throw new ArgoServerException(responseCode);
		}
	}


	private Args() {
		throw new UnsupportedOperationException("Private constructor, cannot be accessed.");
	}

}
