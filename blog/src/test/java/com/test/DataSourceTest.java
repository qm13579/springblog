package com.test;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Slf4j
public class DataSourceTest {
    @Test
    public void stringToPassword(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = passwordEncoder.encode("admin");
        System.out.println(pass);
        log.info("the test password is:{}",pass);
    }
}
