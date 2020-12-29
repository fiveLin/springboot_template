package com.example.lin.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.prefs.BackingStoreException;

/**
 * Created by wujm on 2017/5/16.
 */
@Aspect
@Component
@Order(0)
public class DataSourceInterceptor {
    private static final Logger log = LogManager.getLogger(DataSourceInterceptor.class);

    @Pointcut("execution( * com.example.*.dao.*.*.*(..))")
    public void dataAllSourceBase(){}

    @Before("dataAllSourceBase()")
    public void beforellBase(JoinPoint point) throws BackingStoreException {
        chooseDataSource(point);
    }

    @Pointcut("execution( * com.example..*.service.*.*.*(..))")
    public void dataAllServiceSourceBase(){}

    @Before("dataAllServiceSourceBase()")
    public void beforellServiceBase(JoinPoint point) throws BackingStoreException {
        chooseDataSource(point);
    }

    private void chooseDataSource(JoinPoint point) throws BackingStoreException {
        String className = point.getSignature().getDeclaringTypeName();
        //反射获取对应的类找到对应数据源
        Class classz = null;
        try {
            classz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("类不存在"+className);
            throw new BackingStoreException("类不存在"+className);
        }
        String pack = classz.getPackage().getName();
        String lastPack = pack.substring(pack.lastIndexOf(".")+1);
        boolean find = false;
        for (DataSources dataSources : DataSources.values()) {
            if(dataSources.name().equals(lastPack.toUpperCase())){
                DataSourceTypeManager.set(dataSources);
                find = true;
            }
        }

        if(!find){
            DataSourceTypeManager.reset();
        }
    }

}
