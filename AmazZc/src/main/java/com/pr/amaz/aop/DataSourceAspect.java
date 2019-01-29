package com.pr.amaz.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 使用aop切面动态加载数据源
 */
@Aspect
@Component//表示各种组件
public class DataSourceAspect implements Ordered {

    private Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    /**
     * 切点使用自定义注解@DataSource时执行切面里的方法
     */
    @Pointcut("@annotation(com.pr.amaz.aop.DataSource)")
    public void dataSourcePointCut(){

    }

    /**
     * 围绕切点执行的方法
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        //获取注解中所填写的数据源名称
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSource ds = method.getAnnotation(DataSource.class);
        //如果注解后为空则默认使用idrep注解
        if (ds == null) {
            DynamicDataSource.setDataSource(DataSourceNames.IDREP);
            logger.debug("set datasource is " + DataSourceNames.IDREP);
        } else {
            DynamicDataSource.setDataSource(ds.name());
            logger.debug("set datasource is " + ds.name());
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            logger.debug("clean datasource");
        }
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
