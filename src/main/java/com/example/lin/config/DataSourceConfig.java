package com.example.lin.config;

import com.google.common.collect.Maps;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 时光之里山南水北,你我之间人山人海
 * <p>
 *
 * @author 折骨为刀
 * @create 2019-03-07 下午 01:52
 **/

@Configuration
@PropertySource(value = {"classpath:/application.properties",
        "file:/C:\\lin\\wechat\\db.properties"},
        ignoreResourceNotFound = true)
       // ,"file:/lin/config/wechat/db.properties"
public class DataSourceConfig {
    @Primary
    @Bean(name = "dataSourceWECHAT")
    public ThreadLocalRountingDataSource dataSource() {
        ThreadLocalRountingDataSource dataSource = new ThreadLocalRountingDataSource();
        dataSource.setDefaultTargetDataSource(dataSourceWechat());
        Map<Object, Object> dataSourceList = Maps.newHashMap();
//        dataSourceList.put(DataSources.HTI, dataSourceHti());
//        dataSourceList.put(DataSources.EDU, dataSourceEdu());
        dataSource.setTargetDataSources(dataSourceList);
        return dataSource;
    }
    @Bean
    @ConfigurationProperties(prefix = "db.wechat")
    public Object dataSourceWechat(){
        return new HikariDataSource();
    }


}
