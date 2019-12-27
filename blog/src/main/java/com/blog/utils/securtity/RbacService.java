package com.blog.utils.securtity;

import com.blog.domain.Permission;
import com.blog.domain.Role;
import com.blog.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Slf4j
@Component("rbacService")
public class RbacService {
    @Autowired
    private HttpSession session;
    private AntPathMatcher antPathMatcher =new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication){
        log.info(request.getRequestURI());
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails){ //判断是否是我们的UserDetails对象
            String username = ((UserDetails) principal).getUsername();
            UserInfo userInfo = (UserInfo) session.getAttribute(username);
            Set<String> urls = new HashSet<>();  //读取用户的的所有url
            for (Role role : userInfo.getRoles()){
                for (Permission permission:role.getPermissions()){
                    urls.add(permission.getUrl());
                }
            }
            for (String url: urls){
                if (antPathMatcher.match(url,request.getRequestURI())){
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
