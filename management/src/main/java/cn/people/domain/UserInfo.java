package cn.people.domain;

import cn.people.utils.annotation.Excel;
import cn.people.utils.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author 用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends BaseEntity implements UserDetails{

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


    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public List<Role> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
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
