package com.blog.utils.securtity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
@Slf4j
public class AuthLimtHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        log.info("权限不足");
        httpServletResponse.getWriter().write("{\"code\":\"403\",\"mgs\":\"没有权限\"}");
    }
}
