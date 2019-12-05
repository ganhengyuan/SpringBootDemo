package com.ganhy.util;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author: Gan
 * @ClassName: GenerateId
 * @Date: 2019-12-5 20:17 
 * @Description: 自动生成id更换为自己的即可
 **/
public class GenerateId {

    public GenerateId() {
    }

    public static Long getLongId(){
        return RandomUtils.nextLong();
    }

}
