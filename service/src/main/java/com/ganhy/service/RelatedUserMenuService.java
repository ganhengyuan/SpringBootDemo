package com.ganhy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ganhy.pojo.RelatedUserMenu;

import java.util.List;

/**
 * <p>
 * 用户菜单中间表 服务类
 * </p>
 *
 * @author Ganhy
 * @since 2019-12-05
 */
public interface RelatedUserMenuService extends IService<RelatedUserMenu> {

    /**
     * 通过用户Id获取菜单信息
     * @param userId
     * @return
     */
    List<RelatedUserMenu> selectListByUserId(Long userId);

    /**
     * 给用户添加权限信息
     * @param userId
     * @param menuId
     */
    void addRole(Long userId, Long menuId);

}
