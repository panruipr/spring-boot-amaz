package com.pr.amaz.aop;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {


    /**
     * 用来保存数据源和获取数据源
     * ThreadLocal用来管理多线程，来保证线程的安全，互不干扰。ThreadLocal会在并发编程的时候为每一个线程创建一个副本
     * 虽然共用同一个变量，但每一个线程都会得到相同的初始值。
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<String,DataSource> targetDataSources){
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(new HashMap<Object,Object>(targetDataSources));
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    /**
     * 向ThreadLocal中添加数据源
     * @param dataSource
     */
    public static void setDataSource(String dataSource){
         contextHolder.set(dataSource);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSource(){
        return contextHolder.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource(){
        contextHolder.remove();
    }

}
