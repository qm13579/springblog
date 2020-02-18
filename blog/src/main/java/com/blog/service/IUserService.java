package com.blog.service;

import com.blog.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUserService extends UserDetailsService {
    void save(UserInfo user);

    void loadFile(MultipartFile file) throws IOException;

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}
