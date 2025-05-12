package com.zhyea.argo.config.intercept;

import com.zhyea.argo.action.UserLoginAction;
import com.zhyea.argo.except.ArgoServerException;
import com.zhyea.argo.tools.BeanKit;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.zhyea.argo.constants.Constants.TOKEN_FLAG;
import static com.zhyea.argo.constants.ResponseCode.USER_AUTH_ERROR;
import static com.zhyea.argo.tools.IpKit.clientIp;
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

		String token = request.getHeader(TOKEN_FLAG);
		token = String.valueOf(token).replace("Bearer ", EMPTY);
		String clientIp = clientIp(request);

		checkClientInfo(token, clientIp);

		return true;
	}



	private void checkClientInfo(String token, String ip) {
		if (isBlank(token)) {
			logger.info("Argo Request is blocked, clientToken:{}", token);
			throw new ArgoServerException(USER_AUTH_ERROR);
		}

		try {
			UserLoginAction loginAction = BeanKit.get(UserLoginAction.class);
			loginAction.checkToken(token, ip);
		} catch (ArgoServerException e) {
			throw e;
		} catch (Exception e) {
			logger.info("Argo Request is blocked, check token failed。 clientToken:{}", token, e);
			throw new ArgoServerException(USER_AUTH_ERROR);
		}
	}
}
