package people.cn.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/3/13  下午10:10
 * @description: user DTO
 */
@Data
@NoArgsConstructor
public class UserInfoDto extends UserInfo {

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getCreateDate() {
        return super.getCreateDate();
    }

    @Override
    public List<Role> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public GroupDict getGroup() {
        return super.getGroup();
    }

    @Override
    public Integer getStatus() {
        return super.getStatus();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setCreateDate(String createDate) {
        super.setCreateDate(createDate);
    }

    @Override
    public void setAuthorities(List<Role> roles) {
        super.setAuthorities(roles);
    }

    @Override
    public void setGroup(GroupDict group) {
        super.setGroup(group);
    }

    @Override
    public void setStatus(Integer status) {
        super.setStatus(status);
    }

    @Override
    public void setStatusString(String statusString) {
        super.setStatusString(statusString);
    }


}
