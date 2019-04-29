
package com.cd.shiro.modules.sys.service.impl;


import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.cd.shiro.common.exception.SException;
import com.cd.shiro.common.utils.*;
import com.cd.shiro.modules.sys.dao.SysUserDao;
import com.cd.shiro.modules.sys.entity.SysUserEntity;
import com.cd.shiro.modules.sys.service.SysUserRoleService;
import com.cd.shiro.modules.sys.service.SysUserService;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 系统用户

 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		return baseMapper.queryAllPerms(userId);
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String key = (String)params.get("key");

		Page<SysUserEntity> page = this.selectPage(
			new Query<SysUserEntity>(params).getPage(),
			new EntityWrapper<SysUserEntity>()
				.like(StringUtils.isNotBlank(key),"username", key).or()
				.like(StringUtils.isNotBlank(key),"nickname", key).or()
				.like(StringUtils.isNotBlank(key),"mobile", key)
				.addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
		);

		return new PageUtils(page);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		//String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(DigestUtil.md5Hex(user.getPassword() + Constant.USER_SALT));
		this.insert(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			SysUserEntity userEntity = this.selectById(user.getUserId());
			user.setPassword(DigestUtil.md5Hex(user.getPassword() + Constant.USER_SALT));
		}
		this.updateById(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}


	@Override
	public void updatePassword(String userName, String oldPass, String newPass) {
        SysUserEntity user = findOneByName(userName);
		Assert.isNull(user, "用户名不存在");
		//旧密码加密
		String oldPassHex = DigestUtil.md5Hex(oldPass + Constant.USER_SALT);
		//密码错误
		if(!user.getPassword().equals(oldPassHex)){
			throw new SException("旧密码错误");
		}

		//新密码加密
		String newPassHex = DigestUtil.md5Hex(newPass + Constant.USER_SALT);
		user.setPassword(newPassHex);
		this.updateById(user);
    }

	@Override
	public SysUserEntity findOne(SysUserEntity user) {
		return baseMapper.selectOne(user);
	}

	@Override
	public Map<String, Object> login(String userName, String password) {

		SysUserEntity user = findOneByName(userName);
		Assert.isNull(user, "用户名错误");
		String passHex = DigestUtil.md5Hex(password + Constant.USER_SALT);
		//密码错误
		if(!user.getPassword().equals(passHex)){
			throw new SException("密码错误");
		}
		//修改最后登录IP 时间 登录次数
		String lastIp = IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest());
		user.setLastLoginIp(lastIp);
		user.setLastLoginTime(new Date());
		AtomicInteger atomic = new AtomicInteger(user.getLoginCount());
		user.setLoginCount(atomic.incrementAndGet());//对AtomicInteger原子的加1并返回加1后的值
		this.updateById(user);

		String token = jwtTokenUtil.generateToken(user.getUserId(), userName);
		user.setPassword("");
		Map<String, Object> map = new HashMap<>(2);
		map.put("token", token);
		map.put("expire", jwtTokenUtil.getExpire());
		map.put("user", user);
		return map;
	}

	@Override
	public Map<String, Object> systemCount() {
		List<Map> list = baseMapper.systemCount();
		Map<String, Object> retMap = MapUtil.newHashMap();
		for (Map map: list) {
			String key = map.get("tname").toString();
			String value = map.get("rows").toString();
			if("sys_user".equals(key))
				retMap.put("user", value);//用户表
			if("sys_role".equals(key))
				retMap.put("role", value);//角色表
			if("sys_menu".equals(key))
				retMap.put("menu", value);//菜单表
			if("sys_log".equals(key))
				retMap.put("log", value);//日志表
		}
		return retMap;
	}

	private SysUserEntity findOneByName(String userName) {
		SysUserEntity user = new SysUserEntity();
		user.setUsername(userName);
		return baseMapper.selectOne(user);
	}
}
