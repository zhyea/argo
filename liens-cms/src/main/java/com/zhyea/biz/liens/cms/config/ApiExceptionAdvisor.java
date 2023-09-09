package com.zhyea.biz.liens.cms.config;


import lombok.extern.slf4j.Slf4j;
import org.chobit.commons.enums.CommonStatusCode;
import org.chobit.commons.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author robin
 */
@Slf4j
@ControllerAdvice
public class ApiExceptionAdvisor {


    /**
     * Api异常返回值处理
     *
     * @param e 异常信息
     * @return 封装后的异常返回值
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> exceptionHandler(Exception e) {

        Result<?> r = new Result<>(null);
        r.setMsg("内部异常");

        if (e instanceof MissingServletRequestParameterException) {
            String parameterName = ((MissingServletRequestParameterException) e).getParameterName();
            r.setCode(CommonStatusCode.FAIL.code);
            r.setMsg("请求参数错误，参数名:" + parameterName);
        }
        logger.error("发现内部异常：{}", r.getMsg(), e);
        return r;
    }

}