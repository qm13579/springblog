package cn.people.common;

public enum ResultCodeTest {
    SUCCESS(true,99999,"success"),
    ERROR(false,99998,"error");

    Boolean success;
    int code;
    String message;

    ResultCodeTest(Boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
