/**
 * Copyright (C), 2016-2019
 * FileName: R
 * Author:   cd
 * Date:     2019/4/23 9:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc 返回数据
 * @author cd
 * @create 2019/4/23 9:33
 * @since 1.0.0
 */
public class R extends HashMap<String, Object> {

    public R() {
        put("code", RCode.SUCCESS.getCode());
        put("msg", RCode.SUCCESS.getMessage());
    }

    public static R error() {
        return error(RCode.UNKNOWN_ERROR.getCode(), RCode.UNKNOWN_ERROR.getMessage());
    }

    public static R error(String msg) {
        return error(RCode.SERVER_ERROR.getCode(), msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
