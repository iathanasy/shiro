
package com.cd.shiro.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.cd.shiro.common.utils.PageUtils;
import com.cd.shiro.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 */
public interface SysUserService extends IService<SysUserEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 保存用户
	 */
	void save(SysUserEntity user);
	
	/**
	 * 修改用户
	 */
	void update(SysUserEntity user);

	/**
	 * 修改密码
	 * @param userName       用户名
	 * @param oldPass     原密码
	 * @param newPass  新密码
	 */
	void updatePassword(String userName, String oldPass, String newPass);

	SysUserEntity findOne(SysUserEntity user);

	 Map<String, Object> login(String userName,String password);

	/**
	 * 统计表中的数据
	 * @return
	 */
	Map<String, Object> systemCount();
}
