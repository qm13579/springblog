package com.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class UserInfo implements Serializable{

    private static final long serialVersionUID = 594829320797158219L;
    private String id;
    private String username;
    private String password;
    private Date registerDate;
    private String imgUrl;
    private List<Role> roles;


    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registerDate=" + registerDate +
                ", roles=" + roles +
                '}';
    }
}
