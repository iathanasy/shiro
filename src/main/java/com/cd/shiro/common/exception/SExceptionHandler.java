/**
 * Copyright (C), 2016-2019
 * FileName: SExceptionHandler
 * Author:   cd
 * Date:     2019/4/23 10:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.common.exception;

import com.cd.shiro.common.utils.R;
import com.cd.shiro.common.utils.RCode;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @desc 全局异常处理
 * @author cd
 * @create 2019/4/23 10:34
 * @since 1.0.0
 */
@ControllerAdvice
@RestControllerAdvice
public class SExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest req){
        R r;
        //业务异常
        if(e instanceof SException) {
            r = R.error(RCode.SERVER_ERROR.getCode() ,e.getMessage());
            logger.info(e.getMessage());
        }else if(e instanceof ShiroException) { //Token
            r = R.error(RCode.UNAUTHORIZED.getCode(), "您没有权限");
            logger.info(e.getMessage());
        }else if(e instanceof DuplicateKeyException){
            String msg = "数据库中已存在该记录";
            r = R.error(RCode.BAD_REQUEST.getCode(),msg);
            logger.info(msg);
        }else if (e instanceof NoHandlerFoundException) {
            String msg = "接口 [" + req.getRequestURI() + "] 不存在";
            r = R.error(RCode.NOT_FOUND.getCode(), msg);
            logger.info(msg);
        }else if (e instanceof HttpRequestMethodNotSupportedException) {
            //接口访问方式错误
            r = R.error(RCode.METHOD_NOT_ALLOWD.getCode(), e.getMessage());
            logger.info("接口 ["+req.getRequestURL()+"] ,"+e.getMessage());

        } else{ //系统异常
            r = R.error("接口：["+req.getRequestURL()+"],服务器异常");
        }

        //使用HttpServletRequest中的header检测请求是否为ajax, 如果是ajax则返回json, 如果为非ajax则返回view(即ModelAndView)
        /*String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");
        if ((contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                || (acceptHeader != null && acceptHeader.contains("application/json"))
                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
            return r;
        }*/
        return r;
    }

}
