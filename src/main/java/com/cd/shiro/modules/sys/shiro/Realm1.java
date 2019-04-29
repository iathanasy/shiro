/**
 * Copyright (C), 2016-2019
 * FileName: UserRealm
 * Author:   cd
 * Date:     2019/4/23 16:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.modules.sys.shiro;

import com.cd.shiro.modules.sys.dao.SysMenuDao;
import com.cd.shiro.modules.sys.dao.SysUserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * @desc 用户认证授权 测试
 * @author cd
 * @create 2019/4/23 16:54
 * @since 1.0.0
 */
@Configuration
public class Realm1 extends AuthorizingRealm {

/*    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysMenuDao sysMenuDao;*/

    /**
     * 授权(验证权限时调用)
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String)principalCollection.getPrimaryPrincipal();
        System.out.println("授权： "+ username);
        System.out.println(principalCollection.toString());
        //我们可以通过用户名从数据库获取权限/角色信息
        //角色
        Set<String> r = new HashSet<String>();
        r.add("role1");
        r.add("role2");
        r.add("role3");
        authorizationInfo.setRoles(r);

        //权限
        Set<String> s = new HashSet<String>();
        s.add("user:create");
        s.add("user:delete");
        s.add("user:update");
        s.add("user:view");
        authorizationInfo.setStringPermissions(s);
        return authorizationInfo;
    }

    /**
     * 认证(登录时调用)
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();  //得到用户名
        String password = new String((char[])authenticationToken.getCredentials()); //得到密码

        System.out.println("认证："+ username+", "+ password);

        if(!"zhang".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    public void load(){
        //初始化SecurityManager对象
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //通过SecurityManager工厂对象,获取SecurityManager实例对象.
        SecurityManager securityManager =  factory.getInstance();

        // 把 securityManager 实例 绑定到 SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);

        //组建Subject主体.
        Subject subject = SecurityUtils.getSubject();

    }

}
