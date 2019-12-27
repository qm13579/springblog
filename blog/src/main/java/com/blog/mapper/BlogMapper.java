package com.blog.mapper;

import com.blog.domain.Blog;
import com.blog.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BlogMapper {

    @Select("select * from blog where user_info=#{userId} and status = 1")
    @Results({
            @Result(property = "userInfo",column = "user_info",javaType = UserInfo.class,one = @One(select = "com.blog.mapper.UserMapper.userIdFind"))
    })
    List<Blog> findPublishAll(String userId);

    @Update("update blog set context = #{context}")
    void updateUserPublishBlog(Blog blog);

    @Update("update blog set status = 2 where id = #{id}")
    void removedUserBlog(String id);

    @Insert("insert into blog(id,title,date,context,user_info,status) values(#{id},#{title},#{date},#{context},#{userInfo.id},#{status})")
    void addUserBlog(Blog blog);

    @Select("select * from blog where status = 1")
    @Results({
            @Result(property = "userInfo",column = "user_info",javaType = UserInfo.class,one = @One(select = "com.blog.mapper.UserMapper.userIdFind"))
    })
    List<Blog> findAllBlog();
}
