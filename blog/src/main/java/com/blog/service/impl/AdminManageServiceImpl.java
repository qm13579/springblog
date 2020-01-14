package com.blog.service.impl;

import com.blog.domain.Permission;
import com.blog.domain.Role;
import com.blog.domain.UserInfo;
import com.blog.mapper.IAdminManageMapper;
import com.blog.mapper.PermissionMapper;
import com.blog.mapper.RoleMapper;
import com.blog.service.IAdminManageService;
import com.blog.utils.common.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * admin管理员控制用户角色更新、角色CRUD、权限的CRUD
 */
@Slf4j
@Service
public class AdminManageServiceImpl implements IAdminManageService {

    @Resource
    private IAdminManageMapper adminManageMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Autowired
    private HttpSession session;
    @Override
    public void roleSave(Role role) {
        roleMapper.roleSave(role);
    }

    /**
     * 更新用户角色
     * @param userInfo
     */

    @Override
    public void saveUserByRoles(UserInfo userInfo) {
        //查询用户角色，取差集

        //获取用户的所有角色ID，在session中获取用户信息，得到角色信息
        UserInfo userSession = (UserInfo) session.getAttribute(userInfo.getUsername());
        List<Role> roleList = userSession.getRoles();
        HashSet<String> roleSet = new HashSet<>();
        for (Role role:roleList) {
            roleSet.add(role.getId());
        }
        //获取用户的更新角色ID
        List<Role> userRoles = userInfo.getRoles();
        HashSet<String> userSet = new HashSet<>();
        //深copy userRole
        HashSet<String> userSetCopy = new HashSet<>();

        for (Role userRole:userRoles) {
            userSet.add(userRole.getId());
            userSetCopy.add(userRole.getId());//深copy
        }
        // 当用户角色 > 角色列表 ==> 新增用户
        userSet.removeAll(roleSet);
        if( userSet!= null){
            for (String diffAddRoleId :userSet) {
                log.info("用户角色"+diffAddRoleId);
                roleMapper.addDiffRole(diffAddRoleId,userInfo.getId());
            }
        }
        log.info(userSetCopy.toString());
        log.info(roleSet.toString());
        //恢复set

        //当用户角色 < 角色列表 ==> 删除用户角色
        roleSet.removeAll(userSetCopy);
        if (roleSet != null){
            for (String diffDeleteRoleId :roleSet) {
                log.info("删除用户角色："+diffDeleteRoleId);
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
