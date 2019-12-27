package com.blog.service;

import com.blog.domain.UserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUserService {
    void save(UserInfo user);

    void loadFile(MultipartFile file) throws IOException;
}
