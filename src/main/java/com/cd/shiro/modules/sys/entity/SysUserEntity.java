package com.cd.shiro.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 * 
 * @author cd
 * @email 14163548@qq.com
 * @date 2019-04-23 15:42:21
 */
@TableName("sys_user")
@Data
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户昵称
	 */
	private String nickname;
	/**
	 * 联系人 （用户真实姓名）
	 */
	private String contact;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;
	/**
	 * 用户备注
	 */
	private String remark;
	/**
	 * 最近登录IP
	 */
	private String lastLoginIp;
	/**
	 * 最近登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 登录次数
	 */
	private Integer loginCount;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 角色ID列表
	 */
	@TableField(exist=false)
	private List<Long> roleIdList;

}
