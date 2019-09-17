package com.turing.springbootintegrate.config;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 配置监控拦截器，druid监控拦截器
 * @author ycyoes
 * @version 2019-09-17
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
    initParams = {@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.css,*.ico,/druid/*")})   //忽略资源
public class DruidStatFilter extends WebStatFilter {
}
