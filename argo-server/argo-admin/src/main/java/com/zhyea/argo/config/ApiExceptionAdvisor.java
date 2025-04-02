package com.zhyea.argo.config;

import com.zhyea.argo.constants.ResponseCode;
import com.zhyea.argo.except.ArgoServerException;
import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.model.response.Result;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.stream.Collectors;

import static org.chobit.commons.constans.Symbol.COMMA;
import static org.chobit.commons.constans.Symbol.EMPTY;
import static org.chobit.commons.utils.StrKit.isBlank;
import static org.chobit.commons.utils.StrKit.isNotBlank;


/**
 * Api返回值包装切面定义
 *
 * @author robin
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionAdvisor {


	/**
	 * 自定义业务异常返回值处理
	 *
	 * @param e 异常信息
	 * @return 封装后的异常返回值
	 */
	@ResponseBody
	@ExceptionHandler(ArgoServerException.class)
	public Object argoServerExceptionHandler(ArgoServerException e) {

		Result<?> r = new Result<>(e.getCode());
		r.setMsg(e.getMessage());

		logger.warn("发现服务端异常：{}", r.getMsg(), e);

		return r;
	}


	/**
	 * 参数校验异常返回值处理
	 *
	 * @param ex 异常信息
	 * @return 封装后的异常返回值
	 */
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result<?> paramExceptionHandler(MethodArgumentNotValidException ex) {

		String msg = EMPTY;
		if (ex.hasFieldErrors()) {
			msg = (null == ex.getFieldError() ? EMPTY : ex.getFieldError().getDefaultMessage());
		} else if (ex.hasGlobalErrors()) {
			msg = (null == ex.getGlobalError() ? EMPTY : ex.getGlobalError().getDefaultMessage());
		}

		String logMsg = ex.getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.joining(COMMA));

		Result<?> r = new Result<>(ResponseCode.ARGUMENT_ERROR.code);
		r.setMsg(msg);

		logger.warn("请求参数错误, total:{}, detail:{}", ex.getErrorCount(), logMsg);

		return r;
	}


	/**
	 * 参数校验异常返回值处理
	 *
	 * @param ex 异常信息
	 * @return 封装后的异常返回值
	 */
	@ResponseBody
	@ExceptionHandler(ValidationException.class)
	public Result<?> paramExceptionHandler(ValidationException ex) {
		String msg = ex.getMessage();
		if (null != ex.getCause() && isNotBlank(ex.getCause().getMessage())) {
			msg = ex.getCause().getMessage();
		}

		if (isBlank(msg)) {
			msg = "请求参数错误";
		}

		Result<?> r = new Result<>(ResponseCode.ARGUMENT_ERROR.code);
		r.setMsg(msg);

		logger.warn("请求参数错误", ex);

		return r;

	}


	/**
	 * 未知BindException处理
	 *
	 * @param ex 异常信息
	 * @return 封装后的异常返回值
	 */
	@ResponseBody
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Result<?> bindExceptionHandler(BindException ex) {

		String msg = ex.getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.joining(COMMA));

		Result<?> r = new Result<>(ResponseCode.ERROR.code);
		r.setMsg("未知异常");

		logger.error("发现未知异常: {}", msg, ex);

		return r;
	}


	/**
	 * 未知异常返回值处理
	 *
	 * @param e 异常信息
	 * @return 封装后的异常返回值
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Result<?> exceptionHandler(Exception e) {

		Result<?> r = new Result<>(ResponseCode.ERROR.code);
		r.setMsg("未知异常");

		logger.error("发现未知异常: {}", r.getMsg(), e);

		return r;
	}

}
