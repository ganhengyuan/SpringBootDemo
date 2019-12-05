package com.ganhy.security.authentication;

import com.ganhy.pojo.User;
import com.ganhy.pojo.Menu;
import com.ganhy.pojo.security.UserSecurity;
import com.ganhy.service.MenuService;
import com.ganhy.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Ganhy
 * @date 2019/7/29 16:09
 * @description 权限框架需要的用户登录
 **/
@Component
public class UserDetailsSecurity implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    /**
     * 主要用来用户登录时
     * 判断用户登录名密码是否正确
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        //根据用户名获取用户信息
        User userByUsername = userService.getUserByUsername(username);
        //Java8 的特性, 其实也没什么用, 看着花里胡哨
        Optional<User> userFromDatabase = Optional.ofNullable(userByUsername);
        //判断是否到用户
        if(userFromDatabase.isPresent()){
            //获取用户信息
            User user = userFromDatabase.get();
            //根据用户获取用户权限信息
            List<Menu> userMenus =  menuService.selectMenuList(user.getId());
            Set<String> set = new TreeSet<>();
            for (Menu listMenu : userMenus) {
                set.add(listMenu.getUrlNickName());
            }
            Optional<UserSecurity> userSecurity = Optional.of(
                    //这是创建安全框架所需要的对象
                    new UserSecurity(user.getUsername(),user.getPassword(),
                    true, true, true, true,
                    //说说这, 是赋予用户权限的地方, 方法的作用是, 将集合形式的权限信息 用,拼接起来 并且改为特定格式
                    AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.join(set, ","))
            ));
            userSecurity.get().setId(user.getId());
            return userSecurity.get();
        }else{
            throw new AccountExpiredException("用户"+username+"不存在!");
        }

    }

}
