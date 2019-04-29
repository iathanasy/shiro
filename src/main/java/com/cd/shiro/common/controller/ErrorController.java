/**
 * Copyright (C), 2016-2019
 * FileName: ErrorController
 * Author:   cd
 * Date:     2019/4/25 9:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.common.controller;

import com.cd.shiro.common.utils.R;
import com.cd.shiro.common.utils.RCode;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc errorController 处理
 * @author cd
 * @create 2019/4/25 9:52
 * @since 1.0.0
 */
@RestController
@Api(value = "handle filter throws exception", description = "处理filter抛出的异常")
public class ErrorController extends BasicErrorController{

    public ErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",body.get("status"));
        map.put("msg",body.get("message"));
        //判断Token失效或错误
        if(RCode.TOKENINVALID.getMessage().equalsIgnoreCase(body.get("message").toString())){
            map.put("code",RCode.TOKENINVALID.getCode());
        }
        return new ResponseEntity<Map<String, Object>>(map, status);
    }

}
