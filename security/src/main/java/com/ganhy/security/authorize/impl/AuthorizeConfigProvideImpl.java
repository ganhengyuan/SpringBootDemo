package com.ganhy.security.authorize.impl;

import com.ganhy.security.authorize.AuthorizeConfigProvide;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author Gan
 * @ClassName AuthorizeConfigProvideImpl
 * @Date 2019-8-4 17:04
 * 功能描述：主要被用来当做放行 UIR 的地方了
 **/
@Component
@Order(Integer.MIN_VALUE)
public class AuthorizeConfigProvideImpl implements AuthorizeConfigProvide {

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        //不用登陆就可以访问的URL, swagger2的美化包 swagger-bootstrap-ui 的部分
        config.antMatchers("/swagger-resources", "/v2/api-docs", " /v2/api-docs-ext", "/doc.html", "/webjars/**", "classpath:/static/**")
                .permitAll();
        //登录后才能访问的路径
        config.antMatchers("/manager/**").authenticated();
    }

}
