package cn.people.mapper;

import cn.people.domain.Role;
import cn.people.domain.provider.UserProvider;
import org.apache.ibatis.annotations.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/15  下午8:25
 * @description: 角色相关方式
 */
public interface RoleMapper {

    @Insert("insert into user_role(role_id,user_id) values(#{role_id},#{user_id})")
    public void insertRole(String role_id,String user_id);

    @InsertProvider(type = UserProvider.class,method = "insertRoleByUseId")
    public void insertRoleList(List<String> list,String userId);

    @Select("select * from role where roleName = #{roleName}")
    @Results({
            @Result(property = "desc" ,column = "desc_info")
    })
    public Role findRole(String roleName);

    /**
     *
     * @param uid
     * @return
     */
    @Select("select * from role where id in (select role_id from user_role where user_id=#{uid})")
    @Results({
            @Result(property = "desc" ,column = "desc_info")
    })
    List<Role> findRoleByUserId(String  uid);

    @Update("update role set roleName=#{roleName},desc_info=#{desc} where id=#{id}")
    void updateRole(Role role);

    @Insert("insert into role(id,roleName,desc_info) values(#{id},#{roleName},#{desc})")
    void addRole(Role role);

    @Select("select * from role")
    @Results({
            @Result(property = "desc" ,column = "desc_info")
    })
    List<Role> findAllRoles();

    /**
     * 删除用户对应的角色
     * @param uid
     */
    @Delete("delete from user_role where user_id=#{uid}")
    void deleteRoleByUserId(String uid);

}
