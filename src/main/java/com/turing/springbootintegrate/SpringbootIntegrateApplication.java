package com.turing.springbootintegrate;

import com.turing.springbootintegrate.config.MyBatisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动类
 * @author ycyoes
 * @version 2019-09-17
 */
@SpringBootApplication(scanBasePackages = {"com.turing.springbootintegrate"},
    exclude = {DataSourceAutoConfiguration.class})      //禁用数据源默认配置
public class SpringbootIntegrateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootIntegrateApplication.class, args);
    }

}
