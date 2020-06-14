package cn.people.mapper;

import cn.people.domain.GroupDict;
import cn.people.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/14  下午3:29
 * @description: 用户持久层
 */
public interface IUserMapper {
    /**
     * 查找用户组
     * @return
     */
    @Select("select * from group_dict where gid=1")
    public List<GroupDict> findGroupUser();

    @Insert("insert into user(id,username,password,createDate,groupDict,status,name) " +
            "values(#{id},#{username},#{password},#{createDate},#{group.id},#{status},#{name})")
    public void addUser(UserInfo userInfo);

    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "group",column = "groupDict",javaType =GroupDict.class,one = @One(select = "cn.people.mapper.GroupDictMapper.findGroupById")),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.people.mapper.RoleMapper.findRoleByUserId" ))
    })
    List<UserInfo> findAllUser();

    @Select("select * from user where username = #{username}")
    UserInfo finUserByUsername(String username);

    @Select("select * from user where id = #{udi}")
    UserInfo findUserById(String uid);

    /**
     * 更新用户信息
     * @param user
     */
    @Update("update user set password=#{password},groupDict=#{group.id},status=#{status} where id=#{id}")
    void updateUser(UserInfo user);

    @Update("update user set status = 1 where id = #{uid}")
    void deleteUser(String uid);

    @Select("select * from user where groupDict = #{gid}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "group",column = "groupDict",javaType =GroupDict.class,one = @One(select = "cn.people.mapper.GroupDictMapper.findGroupById")),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.people.mapper.RoleMapper.findRoleByUserId" ))

    })
    List<UserInfo> finUserByGroupiD(String gid);

    /**
     * 增加部门
     * @param group
     */
    @Insert("insert into group_dict(id,groupName,gid) values(#{id},#{groupName},1)")
    void addGroupToUser(GroupDict group);

    /**
     * 更新部门
     * @param group
     */
    @Update("update group_dict set groupName=#{groupName} where id=#{id}")
    void updateGroupToUser(GroupDict group);
    /**
     * 通过用户名查找用户
     * @param s
     * @return
     */
    @Select("select * from user where username = #{s}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "authorities",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.people.mapper.RoleMapper.findRoleByUserId") ),
    })
    UserInfo findUserByUsername(String s);
}
