package people.cn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import people.cn.common.BaseEntity;
import people.cn.common.annotation.Excel;

import java.util.List;

/**
 * @author 用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends BaseEntity{

    public static final String KEY = "user";
    @Excel(name = "ID")
    private String id;

    @Excel(name = "用户名")
    private String username;

    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createDate;
    private List<Role> authorities;
    private GroupDict group;
    private Integer status;
    private String statusString;
    @Excel(name = "姓名")
    private String name;

    public String getStatusString() {
        if (status == 0){
            statusString = "启用";
        }else {
            statusString = "停用";
        }
        return statusString;
    }



    public String getPassword(){
        return password;
    }

    public String getUsername() {
        return username;
    }

    public List<Role> getAuthorities() {
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createDate='" + createDate + '\'' +
                ", authorities=" + authorities +
                ", group=" + group +
                ", status=" + status +
                ", statusString='" + statusString + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
