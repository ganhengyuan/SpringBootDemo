package com.ganhy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ganhy.pojo.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Ganhy
 * @since 2019-12-05
 */
public interface MenuService extends IService<Menu> {

    /**
     * 通过userId获取用户权限信息
     *
     * @param userId
     * @return
     */
    List<Menu> selectMenuList(Long userId);


    /**
     * 创建菜单
     * @param menu
     */
    void createMenu(Menu menu);

}
