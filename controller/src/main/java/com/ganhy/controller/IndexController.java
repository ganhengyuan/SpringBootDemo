package com.ganhy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Gan
 * @ClassName: IndexController
 * @Date: 2019-12-5 15:09
 * @Description: index
 **/
@Api(value = "index访问", tags = "index访问接口,暂时没什么大用", hidden = true)
@Controller
@RequestMapping("")
public class IndexController {

    @ApiOperation(value = "默认访问", httpMethod = "GET", hidden = true)
    @RequestMapping("")
    public String to() {
        return "index";
    }

    @ApiOperation(value = "首页访问", httpMethod = "GET", hidden = true)
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    /**
     * 跳转一个到一个已知页面
     */
    @GetMapping("/html/{html}")
    public String toHtml(@PathVariable("html") String html){
        return html;
    }

    /**
     * 这个页面是从权限框架配置的 SecurityConfig
     * {@link com.ganhy.security.authentication.SecurityConfig}
     * @see com.ganhy.security.authentication.SecurityConfig#configure(HttpSecurity) 中 /403
     */
    @ApiOperation(value = "无权限访问时跳转", httpMethod = "POST", hidden = true)
    @PostMapping("/403")
    public String notRole(){
        return "403";
    }


}
