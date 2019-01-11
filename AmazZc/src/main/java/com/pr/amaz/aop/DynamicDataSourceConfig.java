package com.pr.amaz.aop;
/*
* 配置多数据源
* */
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.idrep")
    public DruidDataSource idrepDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.ircp")
    public DruidDataSource ircpDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.eos75")
    public DruidDataSource eos75DataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource idrepDataSource, DataSource ircpDataSource, DataSource eos75DataSource) {
        Map<String, DataSource> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.IDREP, idrepDataSource);
        targetDataSources.put(DataSourceNames.IRCP,ircpDataSource);
        targetDataSources.put(DataSourceNames.EOS75,eos75DataSource);
        return new DynamicDataSource(idrepDataSource, targetDataSources);
    }

}
