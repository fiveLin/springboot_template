package com.example.lin.config;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.Properties;

/**
 * 时光之里山南水北,你我之间人山人海
 * <p>
 *
 * @author 折骨为刀
 * @create 2019-03-07 下午 02:02
 **/
@Configuration
@MapperScan("com.example.lin.dao")
public class MyBatisConfig {

    private Logger log = LoggerFactory.getLogger(MyBatisConfig.class);

    @Bean
    @Autowired
    @ConfigurationProperties(prefix = "mybatis", ignoreUnknownFields = false)
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSourceEDU") ThreadLocalRountingDataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        //每一张表对应的实体类
        sqlSessionFactoryBean.setTypeAliasesPackage("com.example.lin.pojo");

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //每张表对应的xml文件
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mappers/**/*.xml"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return sqlSessionFactoryBean;
    }

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        //配置mysql数据库的方言
        properties.setProperty("dialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
