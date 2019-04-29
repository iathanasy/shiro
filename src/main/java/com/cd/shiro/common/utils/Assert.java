/**
 * Copyright (C), 2016-2019
 * FileName: Assert
 * Author:   cd
 * Date:     2019/4/23 9:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.common.utils;

import com.cd.shiro.common.exception.SException;
import org.apache.commons.lang.StringUtils;

/**
 * @desc 数据校验
 * @author cd
 * @create 2019/4/23 9:47
 * @since 1.0.0
 */
public abstract class Assert {

    /**
     * 验证字符串为空
     * @param str
     * @param message
     */
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new SException(message);
        }
    }

    /**
     * 验证对象是否为空
     * @param object
     * @param message
     */
    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new SException(message);
        }
    }

    /**
     * 验证对象不为空
     * @param obj
     * @param message
     */
    public static void isNotNull(Object obj, String message) {
        if (null != obj && !obj.equals((Object)null)) {
            throw new SException(message);
        }
    }
}
