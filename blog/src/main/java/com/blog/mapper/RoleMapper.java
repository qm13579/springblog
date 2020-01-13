package com.blog.mapper;

import com.blog.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleMapper  {

    @Select("select * from role where id in (select role_id from user_role where user_id=#{userId})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "permissions" ,column = "id" ,javaType = java.util.List.class,many = @Many(select = "com.blog.mapper.PermissionMapper.findPermissionByRoleId"))
    })
    List<Role> FindRoleByUserId(String userId);

    @Select(" select * from role where role_name=#{roleName} ")
    @Results({
            @Result(property = "roleName",column = "role_name")
    })
    Role findByUserRole(String roleName);

    @Insert("insert into user_role(user_id, role_id) values( #{user_id}, #{diffAddId} )")
    void addDiffRole(@Param("diffAddId")String diffAddId,@Param("user_id")String user_id);

    @Delete("delete from user_role where role_id=#{diffDeleteRoleId} and user_id=#{id}")
    void deleteDiffRole(String diffDeleteRoleId, String id);

    @Insert("insert into role(id,role_name,status) values(#{id},#{roleName},#{status})")
    void roleSave(Role role);

    @Update("update role set id=#{id},role_name=#{roleName}")
    void roleUpdate(Role role);

    @Select("select * from role")
    List<Role> findAll();
}
