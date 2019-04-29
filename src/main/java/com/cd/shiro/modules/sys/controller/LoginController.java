/**
 * Copyright (C), 2016-2019
 * FileName: LoginController
 * Author:   cd
 * Date:     2019/4/23 10:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.modules.sys.controller;

import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import com.cd.shiro.common.utils.Assert;
import com.cd.shiro.common.utils.R;
import com.cd.shiro.common.utils.RCode;
import com.cd.shiro.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @desc
 * @author cd
 * @create 2019/4/23 10:15
 * @since 1.0.0
 */
@RestController
@Api("系统登录接口")
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private SysUserService sysUserService;



    @PostMapping("login")
    @ApiOperation("登录")
    public R login(String userName, String password){
        Assert.isBlank(userName, "用户名不能为空");
        Assert.isBlank(password, "密码不能为空");
        Map<String,Object> map = sysUserService.login(userName,password);
        return R.ok(map);
    }

    @PostMapping("sys/count")
    @ApiOperation("系统统计")
    public R count(){
        Map<String,Object> map = sysUserService.systemCount();
        map.put("version", SystemUtil.getJavaInfo().getVersion());//java版本
        //系统信息
        OsInfo os = SystemUtil.getOsInfo();
        map.put("os", os.getName() + " " + os.getArch());
        //运行时信息
        /*RuntimeInfo run = SystemUtil.getRuntimeInfo();
        map.put("run", run);*/
        return R.ok(map);
    }



    @RequestMapping(path = "/unauthorized/{message}")
    @ApiOperation("无权限时返回")
    public R unauthorized(@PathVariable String message){
        return R.error(RCode.UNAUTHORIZED.getCode(), message);
    }
}
