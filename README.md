# springboot-integrate
spring boot project of integrating

## swagger config
```
@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Doc")
                .description("This is a restful api document of Spring boot.")
                .version("1.0")
                .build();
    }
```
## mybatis config

```
@Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //扫描model
        sessionFactory.setTypeAliasesPackage("com.turing.springbootintegrate.**.model");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //扫描映射文件
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/sqlmap/*.xml"));
        return sessionFactory.getObject();
    }
```
## 使用pagehelper插件进行分页查询
``` yaml
pagehelper:
  helperDialect: mysql
  reasonable: true
  supportMethodsArguments: true
  params: count=countSql
```

## 多数据源配置
相关配置在config/multisource包下，若使用单数据源打开DruidConfig和MyBatisConfig中的@Configuration注解即可

## 添加证书相关配置

使用keytool生成或者阿里云下载证书，在配置文件里进行相关参数配置
### 配置参数
``` 
server:
  port: 443
  ssl:
    key-store: classpath:2831966_www.ycyoes.com.pfx
    enable: true
    key-store-password: 5HxPU0CW
    key-store-type: PKCS12
```

### 添加后缀过滤例外
maven resource插件会将指定后缀外文件进行修改，导致证书文件篡改无法生效，因此需在pom文件中添加例外
```
 <plugin>
 	<groupId>org.apache.maven.plugins</groupId>
 	<artifactId>maven-resources-plugin</artifactId>
 	<configuration>
 		<encoding>UTF-8</encoding>
 		<!-- 过滤后缀为pem、pfx的证书文件 -->
 		<nonFilteredFileExtensions>
 			<nonFilteredFileExtension>pem</nonFilteredFileExtension>
 			<nonFilteredFileExtension>pfx</nonFilteredFileExtension>
 			<nonFilteredFileExtension>p12</nonFilteredFileExtension>
 			<nonFilteredFileExtension>jks</nonFilteredFileExtension>
 		</nonFilteredFileExtensions>
 	</configuration>
 </plugin>
```
### 自定义加解密方法
#####  通过继承Configfilter类重写decrypt方法实现
1. 配置项filters中指定自定义解密类路径；
2. 配置decrypt=true；
3. 多数据源时，需为每个数据源都配置如下两项；

``` 
filters: com.turing.springbootintegrate.filters.SecurityConfigFilter
connectionProperties: config.decrypt=true
```
_自定义config时，不能与默认的config同时使用，否则会使用默认的解密方法，导致无法解密_




