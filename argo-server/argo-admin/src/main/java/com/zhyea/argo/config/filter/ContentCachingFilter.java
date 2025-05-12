package com.zhyea.argo.config.filter;

import com.zhyea.argo.tools.IpKit;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.utils.JsonKit;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.chobit.commons.utils.StrKit.isNotBlank;

/**
 * @author robin
 * @since 2025/5/11 21:56
 */
@Slf4j
public class ContentCachingFilter extends OncePerRequestFilter {


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 包装请求和响应
		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

		String method = request.getMethod();
		String requestString = readRequestStr(request, requestWrapper);
		String clientIp = IpKit.clientIp(request);

		if (!HttpMethod.OPTIONS.matches(method)) {
			logger.info("REQUEST INFO ===========>>> uri:[{}], method:{}, params:[{}], clientIp:[{}]", request.getRequestURI(),
					method, requestString, clientIp);
		}

		try {
			// 执行过滤器链
			filterChain.doFilter(requestWrapper, responseWrapper);
		} finally {
			if (!HttpMethod.OPTIONS.matches(method)) {
				String responseStr = this.readResponseStr(response, responseWrapper);
				logger.info("RESPONSE INFO <<<=========== uri:[{}], method:{}, params:[{}], clientIp:[{}], response:[{}]",
						request.getRequestURI(), method, requestString, clientIp, responseStr);
				// 确保响应被写回客户
				responseWrapper.copyBodyToResponse();
			}
		}
	}


	/**
	 * 读取请求内容
	 *
	 * @param wrapper 请求信息
	 * @return 请求内容
	 * @throws UnsupportedEncodingException 异常
	 */
	private String readRequestStr(HttpServletRequest request, ContentCachingRequestWrapper wrapper) throws UnsupportedEncodingException {
		String requestString;
		if (HttpMethod.GET.matches(request.getMethod())) {
			requestString = JsonKit.toJson(wrapper.getParameterMap());
		} else {
			String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
			if (isNotBlank(contentType) && contentType.contains("multipart/form-data")) {
				requestString = "FILE";
			} else {
				byte[] requestBody = wrapper.getContentAsByteArray();
				requestString = new String(requestBody, request.getCharacterEncoding());
			}
		}
		return requestString;
	}


	/**
	 * 读取响应内容
	 *
	 * @param wrapper 响应信息
	 * @return 响应内容
	 * @throws UnsupportedEncodingException 异常
	 */
	private String readResponseStr(HttpServletResponse response, ContentCachingResponseWrapper wrapper) throws UnsupportedEncodingException {
		String responseString;
		if (wrapper.getContentAsByteArray().length == 0) {
			return "";
		}

		String contentDisposition = response.getHeader(HttpHeaders.CONTENT_DISPOSITION);

		if (isNotBlank(contentDisposition) && contentDisposition.contains("attachment")) {
			responseString = "FILE";
		} else {
			responseString = new String(wrapper.getContentAsByteArray(), response.getCharacterEncoding());
		}
		return responseString;
	}

}
