package com.blog.mapper;

import com.blog.domain.UserInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    @Results({
            @Result(property = "id" ,column = "id"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.blog.mapper.RoleMapper.FindRoleByUserId"))
    })
    UserInfo loadUserByUsername(String username);

    @Insert("insert into user(id,username,password,registerDate) values(#{id},#{username},#{password},#{registerDate})")
    void save(UserInfo user);

    @Select("select * from user where id = #{userId}")
    UserInfo userIdFind(String userId);
}
