

package com.cd.shiro.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.cd.shiro.common.utils.Constant;
import com.cd.shiro.common.utils.PageUtils;
import com.cd.shiro.common.utils.Query;
import com.cd.shiro.modules.sys.dao.SysRoleDao;
import com.cd.shiro.modules.sys.entity.SysRoleEntity;
import com.cd.shiro.modules.sys.service.SysRoleMenuService;
import com.cd.shiro.modules.sys.service.SysRoleService;
import com.cd.shiro.modules.sys.service.SysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * 角色
 *
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String key = (String)params.get("key");

		Page<SysRoleEntity> page = this.selectPage(
			new Query<SysRoleEntity>(params).getPage(),
			new EntityWrapper<SysRoleEntity>()
				.like(StringUtils.isNotBlank(key),"role_name", key)
				.addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
		);


		return new PageUtils(page);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysRoleEntity role) {
		role.setCreateTime(new Date());
		this.insert(role);

		//保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysRoleEntity role) {
		this.updateById(role);

		//更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(Long[] roleIds) {
		//删除角色
		this.deleteBatchIds(Arrays.asList(roleIds));

		//删除角色与菜单关联
		sysRoleMenuService.deleteBatch(roleIds);

		//删除角色与用户关联
		sysUserRoleService.deleteBatch(roleIds);
	}


}
