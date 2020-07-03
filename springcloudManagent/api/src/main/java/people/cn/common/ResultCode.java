package people.cn.common;

public enum ResultCode {

    SUCCESS(true,10000,"成功"),
    FAIL(true,20001,"失败"),
    ERROR(true,29999,"错误");

    boolean success;
    int code;
    String message;


    ResultCode(boolean success, int code, String message) {
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
