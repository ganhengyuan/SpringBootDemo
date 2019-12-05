package com.ganhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author: Gan
 * @ClassName: Application
 * @Date: 2019-12-5 20:31 
 * @Description: SpringBoot启动类
 * 划水划水
 *
 * EnableGlobalMethodSecurity 开启 prePostEnabled 注解
 **/
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
