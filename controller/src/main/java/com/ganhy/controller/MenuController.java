package com.ganhy.controller;

import com.ganhy.pojo.Menu;
import com.ganhy.pojo.result.Result;
import com.ganhy.service.MenuService;
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
 * @ClassName: MenuController
 * @Date: 2019-12-5 14:52 
 * @Description: 菜单控制层
 **/
@Api(value = "菜单控制层", tags = "菜单操作相关的接口")
@PreAuthorize("hasAuthority('menu')")
@RestController
@RequestMapping("manager/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    /**
     * 创建菜单
     */
    @ApiOperation(value = "创建菜单信息", notes="创建菜单信息", httpMethod = "POST", response = Result.class)
    @PreAuthorize("hasAuthority('post:createMenu')")
    @PostMapping("/createMenu")
    public Result createMenu(@ApiParam(name = "menu", value = "菜单信息", required = true) Menu menu) {
        try {
            menuService.createMenu(menu);
        } catch (Exception e) {
            return new Result<>(1011, "error", "菜单创建失败, 信息: " + e.getMessage(), null);
        }
        return new Result<>(1001, "success", "菜单创建成功", null);
    }

}
