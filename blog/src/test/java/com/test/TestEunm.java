package com.test;


/**
 * 变量
 * 构造函数
 *
 */
public enum TestEunm {
    SUCCESS(true,1000,"ok");

    private boolean success;
    private int code;
    private String message;

    TestEunm(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
