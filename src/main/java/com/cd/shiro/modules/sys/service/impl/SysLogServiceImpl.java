
package com.cd.shiro.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cd.shiro.common.utils.PageUtils;
import com.cd.shiro.common.utils.Query;
import com.cd.shiro.modules.sys.dao.SysLogDao;
import com.cd.shiro.modules.sys.entity.SysLogEntity;
import com.cd.shiro.modules.sys.service.SysLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        Page<SysLogEntity> page = this.selectPage(
            new Query<SysLogEntity>(params).getPage(),
            new EntityWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key),"username", key).or()
                    .like(StringUtils.isNotBlank(key),"operation", key).orderBy("create_date",false)
        );

        return new PageUtils(page);
    }
}
