package people.cn.system.config;

import org.springframework.beans.factory.annotation.Autowired;

import people.cn.system.server.impl.UserServiceImpl;


public class SecurityConfig  {

    @Autowired
    private UserServiceImpl userService;

}
