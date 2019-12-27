package com.blog.service;

import com.blog.domain.Permission;
import com.blog.domain.Role;
import com.blog.domain.UserInfo;

import java.util.List;

public interface IAdminManageService {

    void roleSave(Role role);

    void saveUserByRoles(UserInfo userInfo);

    void roleUpdate(Role role);

    List<Role> findAll();

    void permissionSave(Permission permission);

    void permissionUpdate(Permission permission);

    void permissionDelete(Permission permission);

    List<Permission> permissionFindAll();
}
