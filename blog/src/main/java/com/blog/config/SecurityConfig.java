package com.blog.config;

import com.blog.service.impl.UserServiceImpl;
import com.blog.utils.securtity.AuthLimtHandler;
import com.blog.utils.securtity.LoginFailureHandler;
import com.blog.utils.securtity.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true,jsr250Enabled = true)
public class SecurityConfig   extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("test").password(new BCryptPasswordEncoder().encode("test")).roles("USER");
        auth.userDetailsService(userService);  //数据库认证
        auth.eraseCredentials(false);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user/register")
                .antMatchers("/websocket/**")
                .antMatchers("/api/**")
                .antMatchers("/");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置登录页面
//        http.formLogin();
        //授权配置
//        http.authorizeRequests().anyRequest().fullyAuthenticated();
        //关闭csrf
//        http.csrf().disable();
        //登录成功后默认跳转路径
//        http.formLogin().defaultSuccessUrl("/home");
        //关闭csrf
        http.csrf().disable();
        //用户认证逻辑
        http
            .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/swagger-ui.html");
        //开启授权认证(不限制权限)
        //http.authorizeRequests().anyRequest().authenticated();
        //限制权限(role权限)
//        http.authorizeRequests()
//                .antMatchers("/user/**").hasRole("ADMIN").anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger**/**","/v2/**","/favicon**","/configuration/**").permitAll()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)");
        //登录成功处理
        http.formLogin().successHandler(new LoginSuccessHandler());
        //登录失败处理
        http.formLogin().failureHandler(new LoginFailureHandler());
        //权限不足处理
        http.exceptionHandling().accessDeniedHandler(new AuthLimtHandler());
        //登录过期策略
//        http.exceptionHandling().authenticationEntryPoint(new LoginExpireHandler());
        //开启跨域共享
        http.cors().and().csrf().disable();

        //异步登录配置
//        http.formLogin().usernameParameter("username")
//                .passwordParameter("password")
//                .loginProcessingUrl("/login");

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
