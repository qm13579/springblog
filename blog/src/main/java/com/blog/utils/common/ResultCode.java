package com.blog.utils.common;

public enum  ResultCode {

    SUCCESS(true,10000,"操作成功"),
    FAIL(false,10001,"操作失败"),
    UNAUTHEBTICATED(false,10002,"您还未登录"),
    UNAUTHORISE(false,10003,"权限不足"),
    SERVER_ERROR(false,99999,"系统繁忙，稍后再试");

    boolean success;
    int code;
    String message;

    ResultCode(boolean success,int code,String message) {
        this.success =success;
        this.code = code;
        this.message= message;
    }
    public boolean success(){
        return success;
    }
    public int code(){
        return code;
    }
    public String message(){
        return message;
    }
}
