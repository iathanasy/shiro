/**
 * Copyright (C), 2016-2019
 * FileName: ShiroIni
 * Author:   cd
 * Date:     2019/4/23 10:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Arrays;

/**
 * @desc
 * @author cd
 * @create 2019/4/23 10:49
 * @since 1.0.0
 */
public class ShiroIni extends BaseTest{
    String configFile = "classpath:shiro.ini";

    /**
     * 角色
     */
    @Test/*(expected = UnauthorizedException.class)*/
    public void testHasRole(){
        login(configFile, "zhang", "123");
        //判断拥有角色 role1
        Assert.assertTrue(subject().hasRole("role1"));
        //判断拥有角色 role1 role2
        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1","role2")));
        //判断没有角色
        Assert.assertFalse(subject().hasAllRoles(Arrays.asList("role4")));


        //拥有角色 失败抛出异常 UnauthorizedException
        subject().checkRole("role1");

        subject().checkRoles("role1","role2");
    }

    /**
     * 权限 zhang
     */
    @Test
    public void testIsPermitted(){
        login(configFile, "zhang", "123");
        //判断拥有权限
        Assert.assertTrue(subject().isPermitted("user:create"));
        Assert.assertTrue(subject().isPermittedAll("user:create","user:delete","user:update","system:user:create"));
        //判断没有权限
        Assert.assertFalse(subject().isPermitted("user:list"));

        //拥有权限 失败抛出异常
        subject().checkPermission("user:create");
        subject().checkPermissions("user:create","user:delete","user:update");
        //没有权限  org.apache.shiro.authz.UnauthorizedException: Subject does not have permission [user:list]
        subject().checkPermission("user:list");
    }

    /**
     * 权限 li
     */
    @Test
    public void testWildcardPermission1(){
        login(configFile, "li", "123");
        subject().checkPermissions("system:user:update","system:user:delete");
        subject().checkPermissions("system:user:update,delete");

        subject().checkPermissions("system:user:create,delete,update:view");
        subject().checkPermissions("system:user:*");
        subject().checkPermissions("system:user");

        subject().checkPermissions("user:view");
        subject().checkPermissions("system:user:view");

        subject().checkPermissions("user:view:1");
        subject().checkPermissions("user:delete,update:1");
        subject().checkPermissions("user:update:1", "user:delete:1");
        subject().checkPermissions("user:update:1", "user:delete:1", "user:view:1");
        subject().checkPermissions("user:auth:1", "user:auth:2");

        subject().checkPermissions("menu:view:1");
        subject().checkPermissions("organization");
        subject().checkPermissions("organization:view");
        subject().checkPermissions("organization:view:1");

        subject().checkPermission("menu:view:1");
        subject().checkPermission(new WildcardPermission("menu:view:1"));
    }




}
