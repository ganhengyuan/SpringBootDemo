package com.ganhy.config.init;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Gan
 * @ClassName: ScanMappers
 * @Date: 2019-12-5 15:07 
 * @Description: 扫描Mapper的类
 **/
@Configuration
@MapperScan(basePackages = "com.ganhy.dao")
public class ScanMappers {

}
