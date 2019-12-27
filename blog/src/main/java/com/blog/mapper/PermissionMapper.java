package com.blog.mapper;

import com.blog.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PermissionMapper {

    @Select("select * from permission where id in (select permission_id from role_permission where role_id=#{roleId} )")
    @Results({
            @Result(property = "permissionName",column = "permission_name")
    })
    List<Permission> findPermissionByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(id,permission_name,url) values(#{id},#{permissionName},#{url})")
    void save(Permission permission);

    @Update("update permission set permission_name=#{permissionName},url=#{url} where id=#{id}")
    void update(Permission permission);
    //多条件删除
    @Delete("delete from permission where id=#{id}")
    void delete(Permission permission);
}
