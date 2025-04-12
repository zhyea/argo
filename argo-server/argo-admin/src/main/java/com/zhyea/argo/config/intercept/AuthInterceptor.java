package com.zhyea.argo.config.intercept;

import com.zhyea.argo.except.ArgoServerException;
import com.zhyea.argo.tools.AuthContext;
import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.utils.ObjKit;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.zhyea.argo.constants.Constants.TOKEN_FLAG;
import static com.zhyea.argo.constants.ResponseCode.USER_AUTH_ERROR;
import static org.chobit.commons.constans.Symbol.EMPTY;
import static org.chobit.commons.utils.StrKit.isBlank;

/**
 * 权限校验拦截器
 *
 * @author robin
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String path = request.getRequestURI();

		logger.info("Argo Request, path:{}", path);

		String token = request.getHeader(TOKEN_FLAG);
		token = String.valueOf(token).replace("Bearer ", EMPTY);

		if (isBlank(token) || ObjKit.nonEquals(token, AuthContext.getToken())) {
			logger.info("Argo Request is blocked, clientToken:{}, serverToken:{} ", token, AuthContext.getToken());
			throw new ArgoServerException(USER_AUTH_ERROR);
		}

		return true;
	}
}
