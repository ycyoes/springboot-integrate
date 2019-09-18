package com.turing.springbootintegrate.config.multisource;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 动态数据源切换拦截器
 * @author ycyoes
 * @version 2019-09-18
 */
@Aspect
@Order(-1)  //该切面应当先于@Transactional执行
@Component
public class DynamicDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);
    /**
     * 切换数据源
     * @param point
     * @param dataSource
     */
    @Before("@annotation(dataSource)")
    public void switchDataSource(JoinPoint point, DataSource dataSource) {
        if (!DynamicDataSourceContextHolder.containDataSourceKey(dataSource.value())) {
            logger.info("DataSource[{}] doesn't exit, use default DataSource[{}] ", dataSource.value());
        } else {
            //切换数据源
            DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value());
            logger.info("Switch DataSource to {}, in Method {}", DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
        }
    }

    /**
     * 重置数据源
     * @param point
     * @param dataSource
     */
    @After("@annotation(dataSource)")
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
        //将数据源置为默认数据源
        DynamicDataSourceContextHolder.clearDataSourceKey();
        logger.info("Restore DataSource to {}, in Method {} ", DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
    }
}
