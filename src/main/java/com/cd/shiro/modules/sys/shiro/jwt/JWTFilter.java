/**
 * Copyright (C), 2016-2019
 * FileName: JWTFilter
 * Author:   cd
 * Date:     2019/4/24 15:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.modules.sys.shiro.jwt;

import com.cd.shiro.common.exception.SException;
import com.cd.shiro.common.utils.JwtTokenUtil;
import com.cd.shiro.common.utils.RCode;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @desc 拦截器 preHandle->isAccessAllowed->isLoginAttempt->executeLogin
 * @author cd
 * @create 2019/4/24 15:12
 * @since 1.0.0
 *
 */
@Component
public class JWTFilter extends BasicHttpAuthenticationFilter{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest req, ServletResponse res) throws Exception {
        logger.debug("1.preHandle...");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;

        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 如果带有 token，则对 token 进行检查，否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        logger.debug("2.isAccessAllowed...");
        //判断请求的请求头是否带上 "Token"
        if (isLoginAttempt(request, response)) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                //token 错误
                logger.error("token 错误"+ e.getMessage());
                throw new SException(RCode.TOKENINVALID.getMessage(), RCode.TOKENINVALID.getCode());
            }
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }


    /**
     * 判断用户是否想要登入。
     * 检测 header 里面是否包含 Token 字段
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest req, ServletResponse res) {
        logger.debug("3.isLoginAttempt...");
        HttpServletRequest request = (HttpServletRequest)req;
        //从header中获取token
        String token = getToken(request);
        //存在token返回true
        return StringUtils.isNotBlank(token);
    }

    /**
     * 执行登陆操作
     */
    @Override
    protected boolean executeLogin(ServletRequest req, ServletResponse res) throws Exception {
        logger.debug("4.executeLogin...");
        HttpServletRequest request = (HttpServletRequest)req;
        //从header中获取token
        String token = getToken(request);
        JWTToken jwtToken = new JWTToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(req, res).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 获取token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request){
        //从header中获取token
        String token = request.getHeader(jwtTokenUtil.getHeader());
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter(jwtTokenUtil.getHeader());
        }
        return token;
    }
}
