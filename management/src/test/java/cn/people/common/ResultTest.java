package cn.people.common;

import cn.people.utils.common.Result;
import cn.people.utils.common.ResultCode;

/**
 * @author : FENGZHI
 * create at:  2020/4/24  下午9:01
 * @description:
 */
public class ResultTest {

    private String message;
    private boolean success;
    private Integer code;
    private Object data;

    public ResultTest(ResultCodeTest resultCode ,Object data) {
        this.message = resultCode.message;
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.data = data;
    }

    public ResultTest(ResultCodeTest resultCode) {
        this.message = resultCode.message;
        this.success = resultCode.success;
        this.code = resultCode.code;
    }

    public ResultTest(String message, boolean success, Integer code) {
        this.message = message;
        this.success = success;
        this.code = code;
    }

    public static ResultTest SUCCESS(){
        return new ResultTest(ResultCodeTest.SUCCESS);
    }

    public static ResultTest ERROR(){
        return new ResultTest(ResultCodeTest.ERROR);
    }

    @Override
    public String toString() {
        return "ResultTest{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
