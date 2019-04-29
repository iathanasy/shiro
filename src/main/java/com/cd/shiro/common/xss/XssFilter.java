/**
 * Copyright (C), 2016-2019
 * FileName: XssFilter
 * Author:   cd
 * Date:     2019/4/11 15:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.common.xss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desc XSS过滤
 * @author cd
 * @create 2019/4/11 15:02
 * @since 1.0.0
 */
public class XssFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        //xss 过滤
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
                (HttpServletRequest) request);
        HttpServletResponse response = (HttpServletResponse) res;
        //String origin = (String) servletRequest.getRemoteHost() + ":"+ servletRequest.getRemotePort();
        /*//构造头部信息 设置跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,token,disToken");
        response.setHeader("Access-Control-Allow-Credentials", "true");*/
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {

    }
}
