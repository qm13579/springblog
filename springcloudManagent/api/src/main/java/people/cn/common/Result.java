package people.cn.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author apple
 */
@Data
@NoArgsConstructor
public class Result implements Serializable {
    private String message;
    private Integer code;
    private Boolean success;
    private Object data;

    public Result(ResultCode code) {
        this.message = code.message;
        this.code = code.code;
        this.success = code.success;
    }
    public Result(ResultCode code,Object data) {
        this.message = code.message;
        this.code = code.code;
        this.success = code.success;
        this.data = data;
    }
    public Result(String message, Integer code, Boolean success) {
        this.message = message;
        this.code = code;
        this.success = success;
    }
    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }
    public static Result ERROR(){ return new Result(ResultCode.ERROR); }
    public static  Result FAIL(){
     return new Result(ResultCode.FAIL);
    }


}
