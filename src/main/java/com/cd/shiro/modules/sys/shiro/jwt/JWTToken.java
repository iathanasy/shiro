/**
 * Copyright (C), 2016-2019
 * FileName: JWTToken
 * Author:   cd
 * Date:     2019/4/24 11:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.modules.sys.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @desc 重写 AuthenticationToken
 * @author cd
 * @create 2019/4/24 11:15
 * @since 1.0.0
 */
public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
