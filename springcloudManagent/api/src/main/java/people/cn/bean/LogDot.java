package people.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 前端传入的数据
 * @author : FENGZHI
 * create at:  2020/3/5  下午8:46
 * @description: 日志Dot
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDot implements Serializable {
    /**
     * 每页大小
     */
    private int pageSize;
    /**
     * 页码
     */
    private int pageNum;

    @Override
    public String toString() {
        return "LogDot{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                '}';
    }
}
