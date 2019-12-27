package com.blog.service.impl;

import com.blog.domain.UserInfo;
import com.blog.mapper.UserMapper;
import com.blog.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PermissionServiceImpl implements IPermissionService, UserDetailsService {

    @Autowired
    private PermissionServiceImpl permissionService;
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo user = userMapper.loadUserByUsername(s);

        return null;
    }
}
