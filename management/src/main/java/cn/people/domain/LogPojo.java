package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : FENGZHI
 * create at:  2020/3/5  下午8:06
 * @description: log
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogPojo implements Serializable {
    private String id;
    private String url;
    private String method;
    private String user;
    private String date;
    private String time;


    @Override
    public String toString() {
        return "LogPojo{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", user='" + user + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
