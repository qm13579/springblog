package com.blog.service.impl;

import com.blog.domain.Role;
import com.blog.domain.UserInfo;
import com.blog.mapper.RoleMapper;
import com.blog.mapper.UserMapper;
import com.blog.mapper.UserToRoleMapper;
import com.blog.service.IUserService;
import com.blog.utils.IdWorker;
import com.blog.utils.common.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service

/**
 *
 * @author fengzhi
 * @date 2020/1/21
 *
 */
public class UserServiceImpl implements IUserService,UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserToRoleMapper userToRoleMapper;
    @Autowired
    private IdWorker idWorker;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HttpSession session;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void save(UserInfo user) {
        String id = idWorker.nextId()+"";
        //用户信息
        Date newTime = new Date();
        user.setId(id);
        user.setRegisterDate(newTime);
        user.setPassword( passwordEncoder.encode(user.getPassword()) );
        log.info(user.toString());
        Role role = roleMapper.findByUserRole("USER");
        log.info(role.toString());
        userMapper.save(user);
        userToRoleMapper.saveUserIdRoleId(user.getId(),role.getId());

    }

    @Override
    public void loadFile(MultipartFile file) throws IOException {
        UserDetails currentUser = UserUtils.getCurrentUser();
        log.info(currentUser.getUsername());
        String filename = file.getOriginalFilename();
        String substring = filename.substring(filename.lastIndexOf("."));
        String filePath = "C:\\root\\dev\\stor\\";
        filename = UUID.randomUUID().toString()+substring;
        File dest = new File(filePath + filename);
        file.transferTo(dest);
    }

    /**
     * //验证登录操作
     * @param s:username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo=userMapper.loadUserByUsername(s);
        System.out.println(userInfo);
        if (userInfo==null){
            throw new SecurityException("用户名不存在");
        }
        session.setAttribute(userInfo.getUsername(),userInfo);
        redisTemplate.opsForValue().set(userInfo.getUsername(),userInfo);
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role:roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

}
