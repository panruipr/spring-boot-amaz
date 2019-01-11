package com.pr.amaz.aop;
/*
* 多数据源，切面处理  处理带有注解的方法类
*
* */
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DataSourceAspect implements Ordered {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    //注意：这里的aop代表的是public @interface DataSource这个注解DataSource的包名
    @Pointcut("@annotation(aop.DataSource)")
    public void dataSourcePointCut() {

    }
}
