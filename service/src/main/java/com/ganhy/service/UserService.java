package com.ganhy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ganhy.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ganhy
 * @since 2019-12-05
 */
public interface UserService extends IService<User> {

    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 创建用户
     * @param user
     */
    void createUser(User user);

    /**
     * 登录后通过UserSecurity获取用户信息
     * @return
     * @throws Exception
     */
    User getUserBySecurity() throws Exception;


}
