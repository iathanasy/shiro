/**
 * Copyright (C), 2016-2019
 * FileName: SysListener
 * Author:   cd
 * Date:     2019/4/29 11:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro;

import cn.hutool.system.SystemUtil;

/**
 * @desc
 * @author cd
 * @create 2019/4/29 11:30
 * @since 1.0.0
 */
public class SysListener {

    public static void main(String[] args) {
        System.out.println(SystemUtil.getJvmSpecInfo());
        System.out.println(SystemUtil.getJvmInfo());
        System.out.println(SystemUtil.getOsInfo());
        System.out.println(SystemUtil.getJavaRuntimeInfo());
        System.out.println(SystemUtil.getRuntimeInfo());
        System.out.println(SystemUtil.getHostInfo());
        System.out.println(SystemUtil.getUserInfo());
        System.out.println(SystemUtil.getJavaInfo());
        System.out.println(SystemUtil.getJavaSpecInfo());
    }
}
