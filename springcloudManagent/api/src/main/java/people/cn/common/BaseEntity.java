package people.cn.common;

import com.google.common.collect.Maps;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 基类
 * @author : FENGZHI
 * create at:  2020/5/5  下午10:07
 * @description:
 */
@Data
public class BaseEntity implements Serializable {

    public static final String KEY = "";
    public static final long serialVersionUID = 1L;

    public @interface Create {}
    public @interface Update {}

    public Map<String,Object> params;

    public Map<String, Object> getParams() {
        if (params == null){
            params = Maps.newHashMap();
        }
        return params;
    }
}
