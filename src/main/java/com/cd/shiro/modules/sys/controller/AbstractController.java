

package com.cd.shiro.modules.sys.controller;

import com.cd.shiro.common.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller公共组件
 */
@RestController
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	protected Long getUserId(HttpServletRequest request) {
		String token = getToken(request);
		Claims claims = jwtTokenUtil.getClaimByToken(token);
		Long userId = Long.parseLong(claims.get("userId").toString());
		return userId;
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
