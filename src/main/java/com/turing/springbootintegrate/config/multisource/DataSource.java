package com.turing.springbootintegrate.config.multisource;

import java.lang.annotation.*;

/**
 * 动态数据源注解
 * @author ycyoes
 * @version 2019-09-18
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    //数据源key值
    String value();
}
