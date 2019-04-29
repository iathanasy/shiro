package com.cd.shiro.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户与角色对应关系
 * 
 * @author cd
 * @email 14163548@qq.com
 * @date 2019-04-23 15:42:21
 */
@TableName("sys_user_role")
@Data
public class SysUserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 角色ID
	 */
	private Long roleId;

}
