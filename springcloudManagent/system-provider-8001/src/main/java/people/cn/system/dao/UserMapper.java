package people.cn.system.dao;

import org.apache.ibatis.annotations.Mapper;
import people.cn.bean.GroupDict;
import people.cn.bean.UserInfo;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserInfo> findAllUser();
    /**
     * 查找用户组
     * @return
     */
    List<GroupDict> findGroupUser();

    void addUser(UserInfo userInfo);




    UserInfo findUserById(String uid);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(UserInfo user);

    void deleteUser(String uid);


    List<UserInfo> finUserByGroupiD(String gid);

    /**
     * 增加部门
     * @param group
     */
    void addGroupToUser(GroupDict group);

    /**
     * 更新部门
     * @param group
     */
    void updateGroupToUser(GroupDict group);
    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */

    UserInfo findUserByUsername(String username);
}
