
package com.cd.shiro.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cd.shiro.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 */
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	/**
	 * 统计表中的数据
	 * @return
	 */
	List<Map> systemCount();

}
