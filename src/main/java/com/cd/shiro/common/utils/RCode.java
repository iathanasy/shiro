package com.cd.shiro.common.utils;

public enum RCode {
    SUCCESS(0,"成功"),
    UNKNOWN_ERROR(-1, "未知错误"),
    BAD_REQUEST(400, "数据库异常"),
    UNAUTHORIZED(401, "未授权"),
    TOKENINVALID(403, "TOKEN失效"),
    NOT_FOUND(404, "未找到"),
    METHOD_NOT_ALLOWD(405,"请求方法（GET、POST、HEAD、Delete、PUT、TRACE等）对指定的资源不适用"),
    SERVER_ERROR(500, "服务器异常");

    private int code;
    private String message;

    RCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
