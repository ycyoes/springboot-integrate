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
## mybatis pagehelper
