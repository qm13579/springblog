package cn.people.config;
import cn.people.service.imp.UserServiceImpl;
import cn.people.utils.jwt.JWTAuthenticationFilter;
import cn.people.utils.security.AuthLimitHandler;
import cn.people.utils.security.LoginFailureHandler;
import cn.people.utils.security.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午3:35
 * @description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    /**
     * builder：典型用法1-自定义userDetailsService。2-提供若干个自定义的authenticationProvider
     *                3-设置双亲authenticationManager
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //定义密码验证及默认用户
        auth.inMemoryAuthentication()
                .withUser("test")
                .password(new BCryptPasswordEncoder().encode("test"))
                .roles("USER");
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
        auth.eraseCredentials(false);
    }

    /**
     * 配置安全过滤器:
     *  配置登录界面、授权配置、scrf设置、跳转路径、逻辑认证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/create","/user/download","/user/preview","/user/find").permitAll()
                .antMatchers("/swagger**/**","/webjars/**","/swagger**/**","/v2/**","/favicon**","/configuration/**","/images/**").permitAll()
                .antMatchers("/websocket/**").permitAll()
                .antMatchers("/**/*.html").permitAll()
//                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                .and()
            .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new LoginSuccessHandler())  //未使用jwt
//                .successHandler(new cn.people.utils.jwt.LoginSuccessHandler())//使用jwt
                .failureHandler(new LoginFailureHandler())
                .and()
            .exceptionHandling()
                .accessDeniedHandler(new AuthLimitHandler());
//                .authenticationEntryPoint()登陆过期策略
        // rest 无状态 无session
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //配置token验证过滤器
//        http.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 屏蔽资源验证
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/*.js","/templates/**.html");
    }

    /**
     * 实现角色之间的继承关系
     * @return
     */
    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "Role_admin > Role_test \n Role_test > Role_user ";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    /**
     * 定义加盐策略
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
