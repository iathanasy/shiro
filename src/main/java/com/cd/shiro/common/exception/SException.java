/**
 * Copyright (C), 2016-2019
 * FileName: SException
 * Author:   cd
 * Date:     2019/4/23 9:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         版本号            描述
 */
package com.cd.shiro.common.exception;

/**
 * @desc 自定义异常
 * @author cd
 * @create 2019/4/23 9:45
 * @since 1.0.0
 */
public class SException extends RuntimeException{

    private int code;
    private String message;

    public SException(String message) {
        super(message);
        this.message = message;
    }

    public SException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public SException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public SException(String message,  int code, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
