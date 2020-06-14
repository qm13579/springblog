package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : FENGZHI
 * create at:  2020/2/15  上午10:13
 * @description: 设备使用信息,id,用户信息，设备信息，日期，工单状态
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UseEquipmentInfo implements Serializable {
    private int id;
    private UserInfo user;
    private Equipment equipment;
    private String date;
    private String endDate;
    private Integer status;
    private String statusString;

    public String getStatusString() {
        if (status == 0){
            statusString = " 正在使用";
        } else {
          statusString = "已失效";
        }
        return statusString;
    }

    @Override
    public String toString() {
        return "UseEquipmentInfo{" +
                "id=" + id +
                ", user=" + user +
                ", equipment=" + equipment +
                ", date='" + date + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status=" + status +
                ", statusString='" + statusString + '\'' +
                '}';
    }

}
