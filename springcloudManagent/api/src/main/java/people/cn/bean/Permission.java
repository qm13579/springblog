package people.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : FENGZHI
 * create at:  2020/2/15  上午11:22
 * @description: 权限列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    private String id;
    private String permissionName;
    private String url;
    private String method;
    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
