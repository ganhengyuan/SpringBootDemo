package com.ganhy.security.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author Gan
 * @ClassName AuthorizeConfigProvide
 * @Date 2019-8-4 17:02
 * 功能描述：添加安全框架配置策略
 **/
public interface AuthorizeConfigProvide {

    /**
     * 配置策略
     * @param config
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
