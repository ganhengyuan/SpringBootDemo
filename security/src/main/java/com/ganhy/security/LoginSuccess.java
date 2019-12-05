package com.ganhy.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganhy.pojo.result.Result;
import com.ganhy.pojo.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ganhy
 * @date 2019/7/31 14:07
 * @description 自定义登录成功处理
 **/
@Component
public class LoginSuccess implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Authentication copyAuthentication = authentication;
        UserSecurity principal = (UserSecurity) copyAuthentication.getPrincipal();
        //对用户信息进行脱敏处理
        principal.setPassword(null);
        Result result = new Result<>(11001,"success","登录成功!",copyAuthentication);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
