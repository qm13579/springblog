package people.cn.system.server.impl;

import org.springframework.stereotype.Service;
import people.cn.bean.Role;
import people.cn.system.dao.RoleMapper;
import people.cn.system.server.IRoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/20  下午4:40
 * @description: 角色实现类
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAllRoles() {
        return roleMapper.findAllRoles();
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }

    @Override
    public void addRole(Role role) {
        if (role.getId() != null && role.getRoleName() != null){
            roleMapper.addRole(role);
        }
    }
}
