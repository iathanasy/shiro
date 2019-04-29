/**
 * Copyright (C), 2016-2019
 * FileName: SQLFilter
 * Author:   cd
 * Date:     2019/4/23 9:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.common.xss;

import com.cd.shiro.common.exception.SException;
import org.apache.commons.lang.StringUtils;

/**
 * @desc SQL过滤
 * @author cd
 * @create 2019/4/23 9:41
 * @since 1.0.0
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new SException("包含非法字符");
            }
        }

        return str;
    }
}
