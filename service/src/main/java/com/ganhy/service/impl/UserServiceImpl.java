package com.ganhy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ganhy.dao.UserMapper;
import com.ganhy.pojo.User;
import com.ganhy.entity.security.UserSecurity;
import com.ganhy.service.UserService;
import com.ganhy.util.CustomPasswordEncoder;
import com.ganhy.util.GenerateId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ganhy
 * @since 2019-12-05
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public User getUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 创建用户
     *
     * @param user
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void createUser(User user) {
        user.setId(GenerateId.getLongId());
        user.setDeleteStatus("0");
        CharSequence charSequence = user.getPassword();
        CustomPasswordEncoder encoder = new CustomPasswordEncoder();
        //密码加密
        user.setPassword(encoder.encode(charSequence));
        userMapper.insert(user);
    }

    /**
     * 登录后通过UserSecurity获取用户信息
     *
     * @return
     */
    @Override
    public User getUserBySecurity() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //判断用户是否登录
        if(authentication instanceof AnonymousAuthenticationToken){
            log.info("用户未登录");
            throw new Exception("用户身份已过期, 请登录后操作!");
        }
        UserSecurity principal = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userMapper.selectById(principal.getId());
    }

}
