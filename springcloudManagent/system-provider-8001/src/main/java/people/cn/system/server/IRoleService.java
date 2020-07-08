package people.cn.system.server;

import people.cn.bean.Role;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/20  下午4:37
 * @description: 角色接口
 */
public interface IRoleService {

    List<Role> findAllRoles();

    void updateRole(Role role);

    void addRole(Role role);
}
