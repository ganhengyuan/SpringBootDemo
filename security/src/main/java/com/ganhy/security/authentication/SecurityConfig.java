package com.ganhy.security.authentication;

import com.ganhy.security.LoginFail;
import com.ganhy.security.LoginSuccess;
import com.ganhy.security.authorize.AuthorizeConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Ganhy
 * @date 2019/7/26 10:17
 * @description 权限配置中心
 **/

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 登录成功处理器
     */
    @Autowired
    private LoginSuccess loginSuccess;
    /**
     * 登录失败处理器
     */
    @Autowired
    private LoginFail loginFail;
    /**
     * 读取权限配置
     */
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;
    /**
     * 配置过滤规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()//开启formLogin默认配置
                //post登录接口，登录验证由系统实现
                .loginProcessingUrl("/login")
                //要认证的用户参数名，默认username
                .usernameParameter("username")
                //要认证的密码参数名，默认password
                .passwordParameter("password")
                //用户密码错误跳转接口
                .failureUrl("/login/fail")
                //登录成功跳转接口
                .successHandler(loginSuccess)
                .failureHandler(loginFail)
                .and()
            .logout()//配置注销
                .logoutUrl("/logout")
                //注销成功跳转接口
                .logoutSuccessUrl("/login/logout").permitAll()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
            .csrf()//禁用csrf
                .disable();
        authorizeConfigManager.config(http.authorizeRequests());
    }


}
