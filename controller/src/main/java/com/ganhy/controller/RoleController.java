package com.ganhy.controller;

import com.ganhy.entity.result.Result;
import com.ganhy.service.RelatedUserMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Gan
 * @ClassName: RoleController
 * @Date: 2019-12-5 16:20
 * @Description: 权限管理
 **/
@Api(value = "权限控制层", tags = "权限操作相关的接口")
@PreAuthorize("hasAuthority('role')")
@RestController
@RequestMapping("manager/role")
public class RoleController {

    @Autowired
    private RelatedUserMenuService userMenuService;

    @ApiOperation(value = "赋予用户菜单权限", notes="赋予用户菜单权限", httpMethod = "POST", response = Result.class)
    @PreAuthorize("hasAuthority('post:addRole')")
    @PostMapping("/addRole")
    public Result addRole(@ApiParam(name = "userId", value = "用户ID", required = true) Long userId,
                          @ApiParam(name = "menuId", value = "菜单ID", required = true) Long menuId) {

        if (userId == null || menuId == null) {
            return new Result<>(2011, "error", "数据接收失败", null);
        }
        try {
            userMenuService.addRole(userId, menuId);
        } catch (Exception e) {
            return new Result<>(2001, "success", "权限添加失败, 信息: " + e.getMessage(), null);
        }
        return new Result<>(2001, "success", "权限添加成功", null);
    }

}
