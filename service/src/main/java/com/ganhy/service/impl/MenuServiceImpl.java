package com.ganhy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ganhy.dao.MenuMapper;
import com.ganhy.pojo.Menu;
import com.ganhy.pojo.RelatedUserMenu;
import com.ganhy.service.MenuService;
import com.ganhy.service.RelatedUserMenuService;
import com.ganhy.util.GenerateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Ganhy
 * @since 2019-12-05
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RelatedUserMenuService userMenuService;

    /**
     * 通过userId获取用户权限信息
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public List<Menu> selectMenuList(Long userId) {
        List<RelatedUserMenu> userMenus = userMenuService.selectListByUserId(userId);
        List<Menu> menus = new ArrayList<>(userMenus.size());
        for (RelatedUserMenu userMenu : userMenus) {
            menus.add(menuMapper.selectById(userMenu.getMenuId()));
        }
        return menus;
    }

    /**
     * 创建菜单
     *
     * @param menu
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void createMenu(Menu menu) {
        menu.setId(GenerateId.getLongId());
        menuMapper.insert(menu);
    }


}
