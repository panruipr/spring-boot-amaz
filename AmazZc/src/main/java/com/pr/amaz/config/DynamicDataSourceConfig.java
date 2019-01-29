package com.pr.amaz.config;

import com.pr.amaz.aop.DataSourceNames;
import com.pr.amaz.aop.DynamicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 将数据源交给spring-bean管理
 * @Primary 优先使用idrep数据源，不能将@Primary注解到最后一个方法datasource方法上否则会产生依赖注入
 * com.alibaba.druid.pool.DruidDataSource.class 指定数据源类型，这里主要是防止配置文件找不到如果能找到直接使用
 * DataSourceBuilder.create().build()
 */
@Configuration
public class DynamicDataSourceConfig {

    private Logger logger =  LoggerFactory.getLogger(DynamicDataSource.class);

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid.idrep")
    public DataSource idrepDataSource(){
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.ircp")
    public DataSource ircpDataSource(){
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.eos75")
    public DataSource eos75DataSource(){
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }

    @Bean
    public DynamicDataSource dataSource(DataSource idrepDataSource,DataSource ircpDataSource,DataSource eos75DataSource){
        logger.info("加载DynamicDataSourceConfig中的Primary注解方法添加数据源集合");
        Map<String, DataSource> targetDataSource = new HashMap<String, DataSource>();
        targetDataSource.put(DataSourceNames.IDREP,idrepDataSource);
        targetDataSource.put(DataSourceNames.IRCP,ircpDataSource);
        targetDataSource.put(DataSourceNames.EOS75,eos75DataSource);
        return new DynamicDataSource(idrepDataSource,targetDataSource);

    }
}
