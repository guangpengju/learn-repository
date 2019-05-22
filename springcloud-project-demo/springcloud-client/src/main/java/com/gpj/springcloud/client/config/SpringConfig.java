package com.gpj.springcloud.client.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Value("${mybatis.datasource.url:}")
    private String jdbcUrl;
    @Value("${mybatis.datasource.driverClassName}")
    private String jdbcDriverClassName;
    @Value("${mybatis.datasource.username}")
    private String jdbcUsername;
    @Value("${mybatis.datasource.password}")
    private String jdbcPassword;

    
    @Bean(destroyMethod="close")
    public DataSource dataSourceG() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.jdbcUrl);
        datasource.setUsername(this.jdbcUsername);
        datasource.setPassword(this.jdbcPassword);
        datasource.setDriverClassName(this.jdbcDriverClassName);
        return datasource;
    }
}
