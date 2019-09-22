package com.turing.springbootintegrate;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

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
    
    /**
     * 配置一个 TomcatServletWebServerFactory bean
     * 将http 重定向到 https
     * @return
     */
    @Bean
    public TomcatServletWebServerFactory servletContainer() {

        TomcatServletWebServerFactory  tomcat = new TomcatServletWebServerFactory () {

            @Override
            protected void postProcessContext(Context context) {

                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

    /**
     * 让我们的应用支持HTTP是个好想法，但是需要重定向到HTTPS，
     * 但是不能同时在application.properties中同时配置两个connector，
     * 所以要以编程的方式配置HTTP connector，然后重定向到HTTPS connector
     * @return Connector
     */
    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080); // http端口
        connector.setSecure(false);
        connector.setRedirectPort(7443); // application.properties中配置的https端口
        return connector;
    }
}
