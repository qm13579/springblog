package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author : FENGZHI id，维保人员，电话，维保日期，设备，设备状态，描述
 * create at:  2020/2/22  上午10:06
 * @description: 维保实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance implements Serializable {

    private String Id;
    private String name;
    private String mobile;
    private String date;
    private Equipment equipment;
    private Integer status;
    private String statusString;
    private String describe;

    public String getStatusString() {
        if(status == 0){
            statusString="正常";
        }else {
            statusString = "故障";
        }
        return statusString;
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", date='" + date + '\'' +
                ", equipment=" + equipment +
                ", status=" + status +
                ", statusString='" + statusString + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }


}
