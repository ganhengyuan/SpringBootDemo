package com.ganhy.config.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

/**
 * @author Gan
 * @ClassName initConfig
 * @Date 2019-8-5 17:14
 * 功能描述：emmm  没有注释忘了是干什么的了
 **/
@Configuration
public class InitConfig {

    @Bean(name = "antPathMatcher")
    public AntPathMatcher antPathMatcher(){
        return new AntPathMatcher();
    }

}
