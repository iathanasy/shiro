/**
 * Copyright (C), 2016-2019
 * FileName: SysLogAspect
 * Author:   cd
 * Date:     2019/4/23 16:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.common.aspect;

import cn.hutool.json.JSONUtil;
import com.cd.shiro.common.annotation.SysLog;
import com.cd.shiro.common.utils.HttpContextUtils;
import com.cd.shiro.common.utils.IPUtils;
import com.cd.shiro.common.utils.JwtTokenUtil;
import com.cd.shiro.modules.sys.entity.SysLogEntity;
import com.cd.shiro.modules.sys.entity.SysUserEntity;
import com.cd.shiro.modules.sys.service.SysLogService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @desc 系统日志，切面处理类
 * @author cd
 * @create 2019/4/23 16:34
 * @since 1.0.0
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Pointcut("@annotation(com.cd.shiro.common.annotation.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLogEntity sysLog = new SysLogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if(syslog != null){
            //注解上的描述
            sysLog.setOperation(syslog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try{
            String params = JSONUtil.toJsonStr(args[0]);
            sysLog.setParams(params);
        }catch (Exception e){

        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));

        //用户名
        String username = getUserName(request);
        sysLog.setUsername(username);

        sysLog.setTime(time);
        sysLog.setCreateDate(new Date());
        //保存系统日志
        sysLogService.insert(sysLog);
    }

    protected String getUserName(HttpServletRequest request) {
        String token = getToken(request);
        Claims claims = jwtTokenUtil.getClaimByToken(token);
        String userName = (String) claims.get("userName");
        return userName;
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
