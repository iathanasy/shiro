
package com.cd.shiro.modules.sys.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.cd.shiro.common.annotation.SysLog;
import com.cd.shiro.common.utils.Assert;
import com.cd.shiro.common.utils.PageUtils;
import com.cd.shiro.common.utils.R;
import com.cd.shiro.common.utils.ValidatorUtils;
import com.cd.shiro.modules.sys.entity.SysUserEntity;
import com.cd.shiro.modules.sys.service.SysUserRoleService;
import com.cd.shiro.modules.sys.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@RequestMapping("/password")
	@RequiresPermissions("sys:user:update:pass")
	public R password(String userName,String oldPass,String newPass){

		System.out.println("userName: "+ userName + ", password: "+ oldPass +",newPass:"+ newPass);
		Assert.isBlank(oldPass, "原密码不能为空");
		Assert.isBlank(newPass, "新密码不能为空");
		if(newPass.length() < 6){
			return R.error("密码长度不能小于6位");
		}
		//更新密码
		sysUserService.updatePassword(userName, oldPass, newPass);
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.selectById(userId);
		user.setPassword("");//密码置空
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user);
		
		sysUserService.save(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user);

		sysUserService.update(user);
		
		return R.ok();
	}

	/**
	 * 修改用户状态
	 */
	@SysLog("修改用户状态")
	@RequestMapping("/modifyStatus")
	@RequiresPermissions("sys:user:update:status")
	public R modifyStatus(@RequestBody SysUserEntity user){
		if(null == user){return R.error("参数为空");}
		Long userId = user.getUserId();
		Integer status = user.getStatus();
		Assert.isNull(userId, "用户ID不能为空");
		Assert.isNull(status, "用户状态不能为空");
		sysUserService.updateById(user);
		return R.ok();
	}

	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(HttpServletRequest request,@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId(request))){
			return R.error("当前用户不能删除");
		}

		sysUserService.deleteBatchIds(Arrays.asList(userIds));
		
		return R.ok();
	}
}
