package people.cn.dao;

import org.apache.ibatis.annotations.*;
import people.cn.bean.Role;

import java.util.List;

/**
 * @author : FENGZHI
 * create at:  2020/2/15  下午8:25
 * @description: 角色相关方式
 */
@Mapper
public interface RoleMapper {

    void insertRole(String role_id, String user_id);

//    @InsertProvider(type = UserProvider.class,method = "insertRoleByUseId")
    void insertRoleList(@Param("uid") String uId, @Param("rid") String rid);


    Role findRole(String roleName);

    /**
     *
     * @param uid
     * @return
     */

    List<Role> findRoleByUserId(String uid);

    void updateRole(Role role);

    void addRole(Role role);

    List<Role> findAllRoles();
    /**
     * 删除用户对应的角色
     * @param uid
     */
    void deleteRoleByUserId(String uid);

}
