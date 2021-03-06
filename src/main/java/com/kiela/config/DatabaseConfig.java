package com.kiela.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by pino8 on 2016-10-11.
 */

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages={"com.kiela.persistence"})
public class DatabaseConfig {

    @Autowired
    private ApplicationContext applicationContext;

    //application.yml을 읽어들인다. Map으로 읽으려면 ConfigurationProperties를 사용
    @Value("${datasource.driver-class-name}")String driverClassName;
    @Value("${datasource.url}") String url;
    @Value("${datasource.user-name}") String userName;
    @Value("${datasource.password}") String password;
    @Value("${datasource.initial-size}") int initialSize;
    @Value("${datasource.max-active}") int maxActive;
    @Value("${datasource.max-idle}") int maxIdle;
    @Value("${datasource.min-idle}") int minIdle;
    @Value("${datasource.max-wait}") int maxWait;

    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        org.apache.tomcat.jdbc.pool.DataSource dataSource=new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(maxWait);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setTypeAliasesPackage("com.kiela.domain");
//        sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:META-INF/mybatis/mybatis-config.xml"));
//        sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:META-INF/mybatis/mapper*//***/*//*.xml"));
        return sessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }


}
