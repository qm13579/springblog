package people.cn.config;

import org.springframework.beans.factory.annotation.Autowired;

import people.cn.server.impl.UserServiceImpl;


public class SecurityConfig  {

    @Autowired
    private UserServiceImpl userService;

}
