/**
 * Copyright (C), 2016-2019
 * FileName: ShiroRealm
 * Author:   cd
 * Date:     2019/4/24 11:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.modules.sys.shiro;

import com.cd.shiro.common.exception.SException;
import com.cd.shiro.common.utils.Constant;
import com.cd.shiro.common.utils.JwtTokenUtil;
import com.cd.shiro.common.utils.RCode;
import com.cd.shiro.modules.sys.entity.SysMenuEntity;
import com.cd.shiro.modules.sys.entity.SysUserEntity;
import com.cd.shiro.modules.sys.service.SysMenuService;
import com.cd.shiro.modules.sys.service.SysUserService;
import com.cd.shiro.modules.sys.shiro.jwt.JWTToken;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @desc
 * @author cd
 * @create 2019/4/24 11:20
 * @since 1.0.0
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持JWTToken类型的Token
        return token instanceof JWTToken;
    }


    /**
     *  授权(验证权限时调用)
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.debug("————权限认证————");
        Claims claims = jwtTokenUtil.getClaimByToken(principals.toString());
        Long userId = Long.parseLong(claims.get("userId").toString());
        String userName = (String) claims.get("userName");

        List<String> permsList;
        //超级管理员 拥有最高权限
        if(Constant.SUPER_ADMIN_ROOT.equalsIgnoreCase(userName)){
            /*List<SysMenuEntity> menuList = sysMenuService.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }*/
            permsList = new ArrayList<>();
            permsList.add("*");
        }else{
            permsList = sysUserService.queryAllPerms(userId);
        }

        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            //多个
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }

        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);

        return info;
    }

    /**
     * 认证(登录时调用)
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken){
        logger.debug("————身份认证方法————");
        String token = (String) authcToken.getCredentials();//获取用户传入的token
        Claims claims = jwtTokenUtil.getClaimByToken(token);
        if(claims == null || jwtTokenUtil.isTokenExpired(claims.getExpiration())){
            System.out.println(jwtTokenUtil.getHeader()+ "认证失败！");
            throw new SException(RCode.TOKENINVALID.getMessage(), RCode.TOKENINVALID.getCode());
        }

        Long userId = Long.parseLong(claims.get("userId").toString());
        //查询用户信息
        SysUserEntity user = sysUserService.selectById(userId);

        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("用户不存在！");
        }

        //账号锁定
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, getName());
        return info;
    }

    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        //用于记录登录次数
        super.setCredentialsMatcher(credentialsMatcher);
    }
}
