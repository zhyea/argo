package com.zhyea.argo.config.response;


import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.tools.auth.AuthContext;
import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.utils.JsonKit;
import org.chobit.spring.autoconfigure.rw.ResponseWrapper;
import org.chobit.spring.autoconfigure.rw.Tags;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import static org.chobit.commons.utils.StrKit.join;


/**
 * 返回结果封装
 *
 * @author robin
 * @since 2025/4/26 16:17
 */
@Slf4j
@RestControllerAdvice
public class ResponseWrapperAdvice implements ResponseBodyAdvice<Object> {


	@Override
	public boolean supports(MethodParameter returnType,
	                        Class<? extends HttpMessageConverter<?>> converterType) {
		boolean isApiController = AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), RestController.class)
				|| returnType.hasMethodAnnotation(ResponseBody.class);

		boolean effectiveResponseWrapper = AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseWrapper.class)
				|| returnType.hasMethodAnnotation(ResponseWrapper.class);

		return isApiController && effectiveResponseWrapper;
	}


	@Override
	public Object beforeBodyWrite(@Nullable Object body,
	                              MethodParameter returnType,
	                              MediaType selectedContentType,
	                              Class<? extends HttpMessageConverter<?>> selectedConverterType,
	                              ServerHttpRequest request,
	                              ServerHttpResponse response) {
		if (body instanceof ResultWrapper) {
			return body;
		}
		if (selectedConverterType.equals(StringHttpMessageConverter.class)) {
			ResultWrapper<?> result = new ResultWrapper<>(ResponseCode.SUCCESS.code, body);
			this.setTag(returnType, result);
			return JsonKit.toJson(result);
		}

		ResultWrapper<?> result = new ResultWrapper<>(body);
		this.setTag(returnType, result);

		// 刷新token
		if (AuthContext.isTokenChanged()) {
			result.setAuthToken(AuthContext.getToken());
			AuthContext.resetTokenChangeFlag();
		}

		return result;
	}


	/**
	 * 设置返回结果中的标签
	 */
	private void setTag(MethodParameter returnType, ResultWrapper<?> result) {
		Tags tags = returnType.getMethodAnnotation(Tags.class);
		if (null != tags && null != tags.value() && tags.value().length > 0) {
			result.setTags(join(tags.value()));
		}
	}
}