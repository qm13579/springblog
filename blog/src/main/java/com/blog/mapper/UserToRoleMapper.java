package com.blog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserToRoleMapper {

    @Insert("insert into user_role(user_id,role_id) values(#{userId},#{roleId})")
    void saveUserIdRoleId(String userId,String roleId);

}
