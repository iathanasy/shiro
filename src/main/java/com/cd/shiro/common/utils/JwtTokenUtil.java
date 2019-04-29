/**
 * Copyright (C), 2016-2019
 * FileName: JwtTokenUtil
 * Author:   cd
 * Date:     2019/4/24 10:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.common.utils;

import cn.hutool.core.map.MapUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @desc jwt工具类
 * @author cd
 * @create 2019/4/24 10:50
 * @since 1.0.0
 *
 *
 *  JwtToken生成的工具类
 *  JWT token的格式：header.payload.signature
 *  header的格式（算法、token的类型）：
 *  {"alg": "HS512","typ": "JWT"}
 *  payload的格式（用户名、创建时间、生成时间）：
 *  {"sub":"wang","created":1489079981393,"exp":1489684781}
 *  signature的生成算法：
 *  HMACSHA256(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 *  https://gitee.com/zscat-platform/mall on 2018/4/26.
 *
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "shiro.jwt")
@Component
public class JwtTokenUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String secret;
    private long expire;
    private String header;

    /**
     * 生成jwt token
     */
    public String generateToken(Long userId, String userName) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        Map map = MapUtil.newHashMap();
        map.put("userId", userId);
        map.put("userName", userName);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId+"")
                .setClaims(map)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            logger.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }


}
