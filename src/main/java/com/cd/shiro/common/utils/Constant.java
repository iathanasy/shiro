/**
 * Copyright (C), 2016-2019
 * FileName: Constant
 * Author:   cd
 * Date:     2019/4/23 16:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.common.utils;

import cn.hutool.crypto.digest.DigestUtil;
import org.apache.commons.lang.RandomStringUtils;

/**
 * @desc 常量
 * @author cd
 * @create 2019/4/23 16:02
 * @since 1.0.0
 */
public class Constant {
    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;
    public static final String SUPER_ADMIN_ROOT = "root";
    /** 数据权限过滤 */
    public static final String SQL_FILTER = "sql_filter";
    /**用户密码加盐*/
    public static final String USER_SALT = "NJjMuDEGPF3grQ9Lpk3j";

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
