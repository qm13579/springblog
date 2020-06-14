package cn.people.service;

import cn.people.domain.Role;

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
