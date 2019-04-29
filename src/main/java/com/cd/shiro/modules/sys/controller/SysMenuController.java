
package com.cd.shiro.modules.sys.controller;

import com.cd.shiro.common.annotation.SysLog;
import com.cd.shiro.common.exception.SException;
import com.cd.shiro.common.utils.Constant;
import com.cd.shiro.common.utils.R;
import com.cd.shiro.modules.sys.entity.SysMenuEntity;
import com.cd.shiro.modules.sys.service.SysMenuService;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 导航菜单
	 */
	@RequestMapping("/nav")
	@RequiresPermissions("sys:menu:nav")
	public R nav(HttpServletRequest request){
		String userName = getUserName(request);
		List<SysMenuEntity> menuList;
		if (Constant.SUPER_ADMIN_ROOT.equalsIgnoreCase(userName)) {//超级管理员拥有所有权限
			menuList = sysMenuService.getUserMenuList(null);
		}else {
			menuList = sysMenuService.getUserMenuList(getUserId(request));
		}
		return R.ok().put("menuList", menuList);
	}

	/**
	 * 获取菜单树
	 * @return
	 */
	@RequestMapping("/tree")
	@RequiresPermissions("sys:menu:tree")
	public R menuTree(){
		List<SysMenuEntity> menuList = sysMenuService.findMenuTree();
		return R.ok().put("tree", menuList);
	}

	/**
	 * 所有菜单列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public List<SysMenuEntity> list(){
		List<SysMenuEntity> menuList = sysMenuService.selectList(null);
		for(SysMenuEntity sysMenuEntity : menuList){
			SysMenuEntity parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
			if(parentMenuEntity != null){
				sysMenuEntity.setParentName(parentMenuEntity.getName());
			}
		}

		return menuList;
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public R select(){
		//查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.getSelectMenuList();
		
		//添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("顶级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		root.setList(menuList);
		List list = new ArrayList();
		list.add(root);
		return R.ok().put("menuList", list);
	}
	
	/**
	 * 菜单信息
	 */
	@RequestMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public R info(@PathVariable("menuId") Long menuId){
		SysMenuEntity menu = sysMenuService.selectById(menuId);
		if(null != menu){
			if(0L != menu.getParentId()){
				SysMenuEntity s = sysMenuService.selectById(menu.getParentId());
				menu.setParentName(s.getName());
			}else{
				menu.setParentName("顶级菜单");
			}

		}
		return R.ok().put("menu", menu);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	public R save(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
		
		sysMenuService.insert(menu);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:update")
	public R update(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
				
		sysMenuService.updateById(menu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	public R delete(@RequestParam Long menuId){
		/*if(menuId <= 31){
			return R.error("系统菜单，不能删除");
		}*/

		//判断是否有子菜单或按钮
		List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
		if(menuList.size() > 0){
			return R.error("请先删除子菜单或按钮");
		}

		sysMenuService.delete(menuId);

		return R.ok();
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new SException("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new SException("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == Constant.MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new SException("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = Constant.MenuType.CATALOG.getValue();
		if(menu.getParentId() != 0){
			SysMenuEntity parentMenu = sysMenuService.selectById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == Constant.MenuType.CATALOG.getValue() ||
				menu.getType() == Constant.MenuType.MENU.getValue()){
			if(parentType != Constant.MenuType.CATALOG.getValue()){
				throw new SException("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
			if(parentType != Constant.MenuType.MENU.getValue()){
				throw new SException("上级菜单只能为菜单类型");
			}
			return ;
		}
	}
}
