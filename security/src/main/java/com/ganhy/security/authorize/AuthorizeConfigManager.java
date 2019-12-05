package com.ganhy.security.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author Gan
 * @ClassName AuthorizeConfigManager
 * @Date 2019-8-4 17:06
 * 功能描述：统计所有的配置信息，添加进管理器
 **/
public interface AuthorizeConfigManager {

    /**
     * 配置管理器
     * @param config
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
