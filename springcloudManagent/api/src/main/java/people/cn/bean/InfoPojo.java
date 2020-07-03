package people.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 当用户报故障时，info同时记录信息，并推送给运维人员。运维人员还有单独模块待处理工单
 *
 * 当一个用户-角色把信息读完后，其他用户不能读取到信息
 * id，信息来源，信息内容，状态(已读、未读)
 * @author : FENGZHI
 * create at:  2020/2/22  上午10:23
 * @description: 信息实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoPojo implements Serializable {
    private String id;
    private String source;
    private String content;
    private Integer status;
    private String statusString;
    private String date;
    private List<UserInfo> user;
    private List<Role> roles;
    private List<GroupDict> groupDicts;

    public String getStatusString() {
        if (status == 0){
            statusString ="已推送相关人员";
        }else {
            statusString="未推送相关人员";
        }

        return statusString;
    }

    @Override
    public String toString() {
        return "InfoPojo{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", statusString='" + statusString + '\'' +
                ", date='" + date + '\'' +
                ", user=" + user +
                '}';
    }
}
