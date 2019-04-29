
package com.cd.shiro.modules.sys.controller;
import com.cd.shiro.common.annotation.SysLog;
import com.cd.shiro.common.utils.PageUtils;
import com.cd.shiro.common.utils.R;
import com.cd.shiro.modules.sys.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;


/**
 * 系统日志

 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:log:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysLogService.queryPage(params);

		return R.ok().put("page", page);
	}

	@SysLog("删除日志")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:log:delete")
	public R delete(@RequestBody Long[] logIds){
		sysLogService.deleteBatchIds(Arrays.asList(logIds));
		return R.ok();
	}
}
