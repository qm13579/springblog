package people.cn.server.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import people.cn.bean.GroupDict;
import people.cn.bean.Role;
import people.cn.bean.UserInfo;
import people.cn.common.IdWorker;
import people.cn.common.Result;
import people.cn.common.ResultCode;
import people.cn.dao.GroupDictMapper;
import people.cn.dao.RoleMapper;
import people.cn.dao.UserMapper;
import people.cn.server.IUserService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : FENGZHI
 * create at:  2020/2/14  上午10:35
 * @description: 用户实现类
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Resource
    private UserMapper userMapping;

    @Autowired
    private IdWorker idWorker;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private GroupDictMapper groupDictMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public List<UserInfo> findAllUser() {

        return userMapping.findAllUser();
    }
    @Override
    public UserInfo findUserByUsername(String username){
        return userMapping.finUserByUsername(username);
    }
    @Override
    public UserInfo findUserById(String uid) {

        return userMapping.findUserById(uid);
    }

    @Override
    public void updateUser(UserInfo user) {
        //先删除用户和角色关系
        roleMapper.deleteRoleByUserId(user.getId());
        //建立用户和角色对应冠旭
        List<String> collect = user.getAuthorities().stream().map(Role::getId).collect(Collectors.toList());
//        roleMapper.insertRoleList(collect,user.getId());
        for (Role role :user.getAuthorities()) {
            roleMapper.insertRoleList(user.getId(),role.getId());
        };
//        for (Role role:user.getRoles()) {
//            roleMapper.insertRole(role.getId(),user.getId());
//        }
        userMapping.updateUser(user);
    }

    @Override
    public List<GroupDict> findGroupUser() {

        return userMapping.findGroupUser();
    }

    @Override
    public List<UserInfo> findUsersByGroupId(String gid) {

        return userMapping.finUserByGroupiD(gid);
    }

    /**
     * 停用用户
     * @param uid
     */
    @Override
    public void deleteUser(String uid) {
        userMapping.deleteUser(uid);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result addUser(UserInfo user) {
        Result result = null;

//        if (user.getGroup().getId() ==null || user.getName() == null || user.getPassword() == null){
//            result = new Result(ResultCode.FAIL);
//            result.setMessage("添加用户失败,请填写全部字段");
//            return result;
//        }
        try {
            user.setId(idWorker.nextId()+"");
            user.setCreateDate(DATE.format(new Date()));
            user.setPassword(passwordEncoder.encode("123456"));
            user.setStatus(0);
            userMapping.addUser(user);
            List<Role> roles = user.getAuthorities();
            //新建用户和角色对应关系
            for (Role role:user.getAuthorities()) {
                roleMapper.insertRole(role.getId(),user.getId());
            }
            result = new Result(ResultCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            result = new Result(ResultCode.ERROR);
        }

        return result;
    }

    @Override
    public void addGroupToUser(GroupDict group) {
        group.setId(idWorker.nextId()+"");
        userMapping.addGroupToUser(group);
    }

    @Override
    public void updateGroupToUser(GroupDict group) {
        userMapping.updateGroupToUser(group);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo user = userMapping.findUserByUsername(s);
        if (user == null){
            throw new SecurityException("用户不存在");
        }
        return user;
    }
}
