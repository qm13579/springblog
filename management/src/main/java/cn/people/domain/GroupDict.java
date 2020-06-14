package cn.people.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * admin----dipartment admin ---
 * 添加部门 --> 部门负责人 1-1boos，10 射洪县支行 1   11 金融管理部 10   user-group 11 -gid-射洪县支行-中支
 * 添加管理员 仅仅对当前用户组及一下用户组有效
 * @author : FENGZHI
 * create at:  2020/2/14  上午10:12
 * @description: 用户分组 1：部门 2：设备
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDict implements Serializable {
    private String id;
    private String groupName;
    private String gid;

    @Override
    public String toString() {
        return "GroupDict{" +
                "id='" + id + '\'' +
                ", groupName='" + groupName + '\'' +
                ", gid='" + gid + '\'' +
                '}';
    }

}
