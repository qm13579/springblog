package people.cn.server;


import people.cn.bean.GroupDict;
import people.cn.bean.UserInfo;
import people.cn.common.Result;

import java.util.List;

/**
 * @author fengzhi
 */
public interface IUserService {
    /**
     * 通过username查找用户
     * @param username
     * @return
     */
    public UserInfo findUserByUsername(String username);
    /**
     * 查找所有用户
     * @return
     */
    public List<UserInfo> findAllUser();

    /**
     * 通过用户id查找用户
     * @param uid
     * @return
     */
    public UserInfo findUserById(String uid);

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUser(UserInfo user);

    /**
     * 获取用户分组
     * @return
     */
    public List<GroupDict> findGroupUser();

    /**
     * 通过分组id查找当前组中的用户
     * @param groupId
     * @return userInfo
     */
    public List<UserInfo> findUsersByGroupId(String groupId);

    /**
     * 根据uid删除用户
     * @param uid
     */
    public void deleteUser(String uid);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public Result addUser(UserInfo user);

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
}
