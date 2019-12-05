package com.ganhy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ganhy.dao.RelatedUserMenuMapper;
import com.ganhy.pojo.RelatedUserMenu;
import com.ganhy.service.RelatedUserMenuService;
import com.ganhy.util.GenerateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户菜单中间表 服务实现类
 * </p>
 *
 * @author Ganhy
 * @since 2019-12-05
 */
@Service
public class RelatedUserMenuServiceImpl extends ServiceImpl<RelatedUserMenuMapper, RelatedUserMenu> implements RelatedUserMenuService {

    @Autowired
    private RelatedUserMenuMapper userMenuMapper;

    /**
     * 通过用户Id获取菜单信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<RelatedUserMenu> selectListByUserId(Long userId) {
        QueryWrapper<RelatedUserMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return userMenuMapper.selectList(queryWrapper);
    }

    /**
     * 给用户添加权限信息
     *
     * @param userId
     * @param menuId
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addRole(Long userId, Long menuId) {
        RelatedUserMenu userMenu = new RelatedUserMenu();
        userMenu.setId(GenerateId.getLongId());
        userMenu.setUserId(userId);
        userMenu.setMenuId(menuId);
        userMenuMapper.insert(userMenu);
    }

}
