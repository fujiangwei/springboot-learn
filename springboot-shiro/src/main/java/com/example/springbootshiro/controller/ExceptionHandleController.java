package com.example.springbootshiro.controller;

import com.example.springbootshiro.constants.CommonConstants;
import com.example.springbootshiro.domain.vo.ResponseVO;
import com.example.springbootshiro.enums.ResponseStatusEnum;
import com.example.springbootshiro.utils.ResultUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * 统一异常处理类<br>
 * 捕获程序所有异常，针对不同异常，采取不同的处理方式
 *
 */
@ControllerAdvice
public class ExceptionHandleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandleController.class);

//    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception ex) {
        return "redirect:/error/403";
    }

//    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(Exception ex) {
        return "redirect:/error/401";
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVO handle(Throwable e) {
        e.printStackTrace(); // 打印异常栈
        if (e instanceof UndeclaredThrowableException) {
            e = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
        }
        ResponseStatusEnum responseStatus = ResponseStatusEnum.getResponseStatus(e.getMessage());
        if (responseStatus != null) {
            LOGGER.error(responseStatus.getMessage());
            return ResultUtil.error(responseStatus.getCode(), responseStatus.getMessage());
        }
        return ResultUtil.error(CommonConstants.DEFAULT_ERROR_CODE, ResponseStatusEnum.ERROR.getMessage());
    }

}
