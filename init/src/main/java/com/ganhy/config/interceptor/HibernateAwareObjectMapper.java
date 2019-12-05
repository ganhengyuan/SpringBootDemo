package com.ganhy.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @author Gan
 * @ClassName HibernateAwareObjectMapper
 * @Date 2019-8-16 14:43
 * 功能描述：前端格式转换
 **/
public class HibernateAwareObjectMapper extends ObjectMapper {

    /**
     * 前端Long数字精度丢失问题
     * @return
     */
    private SimpleModule simpleModule(){
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        return simpleModule;
    }

    public HibernateAwareObjectMapper() {
        registerModule(simpleModule());
//        //日期格式化, 好像是不能用还是怎么的, 忘记了
//        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

}
