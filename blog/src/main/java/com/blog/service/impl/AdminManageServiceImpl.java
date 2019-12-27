package com.blog.service.impl;

import com.blog.domain.Permission;
import com.blog.domain.Role;
import com.blog.domain.UserInfo;
import com.blog.mapper.IAdminManageMapper;
import com.blog.mapper.PermissionMapper;
import com.blog.mapper.RoleMapper;
import com.blog.service.IAdminManageService;
import com.blog.utils.common.ListUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * admin管理员控制用户角色更新、角色CRUD、权限的CRUD
 */
@Service
public class AdminManageServiceImpl implements IAdminManageService {

    @Resource
    private IAdminManageMapper adminManageMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public void roleSave(Role role) {
        roleMapper.roleSave(role);
    }

    @Override
    public void saveUserByRoles(UserInfo userInfo) {
        //获取用户的所有角色ID
        List<Role> roleList = roleMapper.FindRoleByUserId(userInfo.getId());
        List<String> roleIdList = new ArrayList<String>();
        for (Role role:roleList) {
            roleIdList.add(role.getId());
        }
        //获取用户的更新角色ID
        List<Role> userRoles = userInfo.getRoles();
        List<String> userRoleIdList = new ArrayList<String>();
        for (Role userRole:userRoles) {
            userRoleIdList.add(userRole.getId());
        }
        //角色列表中不包含用户角色时，添加角色,A差B，求B有A没有
        ListUtils addRole = new ListUtils();
        List<String> differenceSetAdd = addRole.DifferenceSet(roleIdList, userRoleIdList);
        if(differenceSetAdd != null){
            for (String diffAddRoleId :differenceSetAdd) {
                roleMapper.addDiffRole(diffAddRoleId,userInfo.getId());
            }
        }
        //用户角色不含有角色列表,删除角色
        ListUtils deleteRole= new ListUtils();
        List<String> differenceSetDelete = deleteRole.DifferenceSet(userRoleIdList, roleIdList);
        if (differenceSetDelete != null){
            for (String diffDeleteRoleId :differenceSetDelete) {
                roleMapper.deleteDiffRole(diffDeleteRoleId,userInfo.getId());
            }
        }
    }

    @Override
    public void roleUpdate(Role role) {
        roleMapper.roleUpdate(role);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public void permissionSave(Permission permission) {
        permissionMapper.save(permission);
    }

    @Override
    public void permissionUpdate(Permission permission) {
        permissionMapper.update(permission);
    }

    @Override
    public void permissionDelete(Permission permission) {
        permissionMapper.delete(permission);
    }

    @Override
    public List<Permission> permissionFindAll() {
        List<Permission> permissionList = permissionMapper.findAll();
        return permissionList;

    }
}
