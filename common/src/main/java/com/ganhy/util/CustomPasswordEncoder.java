package com.ganhy.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Ganhy
 * @date 2019/7/29 15:19
 * @description 自定义密码加密方式
 **/

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    /**
     * 密码加密
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    /**
     * 对比密码是否正确
     * @param charSequence
     * @param s
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }

}

