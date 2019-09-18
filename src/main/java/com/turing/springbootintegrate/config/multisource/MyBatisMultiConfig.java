package com.turing.springbootintegrate.config.multisource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源配置
 * @author ycyoes
 * @version 2019-09-18
 */
@Configuration
@MapperScan(basePackages = {"com.turing.springbootintegrate.**.dao"})  //扫描DAO
public class MyBatisMultiConfig {

    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave() {
        return DataSourceBuilder.create().build();
    }

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("master", master());
        dataSourceMap.put("slave", slave());
        //将master数据源作为默认指定的数据源
        dynamicDataSource.setDefaultTargetDataSource(master());
        //将master和slave数据源作为指定的数据源
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        //配置数据源，此处配置为关键配置，如果没有将dynamicDataSource作为数据源则不能实现切换
        sessionFactory.setDataSource(dynamicDataSource());
        sessionFactory.setTypeAliasesPackage("com.turing.**.model");    //扫描model
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/sqlmap/*.xml")); //扫描映射文件
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        //配置事务管理，使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
