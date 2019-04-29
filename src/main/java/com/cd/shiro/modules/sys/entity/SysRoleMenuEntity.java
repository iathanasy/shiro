package com.cd.shiro.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色与菜单对应关系
 * 
 * @author cd
 * @email 14163548@qq.com
 * @date 2019-04-23 15:42:21
 */
@TableName("sys_role_menu")
@Data
public class SysRoleMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 菜单ID
	 */
	private Long menuId;

}
