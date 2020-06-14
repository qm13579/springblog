package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

/**
 * id rolename：角色名，desc：描述角色用途
 * 管理员、一般用户、部门负责人，部门管理员、运维人员、部门运维人员
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {
    private String id;
    private String roleName;
    private String desc;
    private List<Permission> permissions;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
