package com.ganhy.security.authorize.impl;

import com.ganhy.security.authorize.AuthorizeConfigManager;
import com.ganhy.security.authorize.AuthorizeConfigProvide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Gan
 * @ClassName AuthorizeConfigManagerImpl
 * @Date 2019-8-4 17:07
 * 功能描述：循环将所有的配置信息，添加进管理器
 **/
@Component
public class AuthorizeConfigManagerImpl implements AuthorizeConfigManager {

    @Autowired
    private List<AuthorizeConfigProvide> authorizeConfigProvides;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvide authorizeConfigProvide : authorizeConfigProvides) {
            authorizeConfigProvide.config(config);
        }
    }

}
