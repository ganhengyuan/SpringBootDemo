package com.ganhy.controller;

import com.ganhy.pojo.User;
import com.ganhy.pojo.result.Result;
import com.ganhy.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Gan
 * @ClassName: UserController
 * @Date: 2019-12-5 14:45 
 * @Description: 用户控制层
 **/
@Api(value = "用户控制层", tags = "用户相关操作对外的接口")
@PreAuthorize("hasAuthority('user')")
@RestController
@RequestMapping("manager/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 创建用户
     */
    @ApiOperation(value = "创建用户", notes="创建用户", httpMethod = "POST", response = Result.class)
    @PreAuthorize("hasAuthority('user:createUser')")
    @PostMapping("/createUser")
    public Result createUser(@ApiParam(name = "user", value = "用户信息", required = true) User user) {
        try {
            userService.createUser(user);
        } catch (Exception e) {
            return new Result<>(1011, "error", "用户创建失败, 信息: " + e.getMessage(), null);
        }
        return new Result<>(1011, "success", "用户创建成功", null);
    }

    /**
     * 获取登录用户信息
     */
    @ApiOperation(value = "获取登录用户信息", notes="获取登录用户信息", httpMethod = "GET", response = Result.class)
    @PreAuthorize("hasAuthority('user:getSecurityUser')")
    @GetMapping("/getSecurityUser")
    public Result getSecurityUser() {
        User userBySecurity;
        try {
            userBySecurity = userService.getUserBySecurity();
        } catch (Exception e) {
            return new Result<>(1011, "error", "用户信息获取失败, 信息: " + e.getMessage(), null);
        }
        return new Result<>(1011, "success", "用户信息获取成功", userBySecurity);
    }


}
