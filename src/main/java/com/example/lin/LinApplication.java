package com.example.lin;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName DemoApplication
 * @Description : EnableScheduling开启定时器（@Scheduled）
 * @EnableTransactionManagement开启事务支持(@Transactional)
 * @Author : Ljh
 * @Date : 2019/11/22 14:51
*/
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class},
		scanBasePackages = {"com.example.lin"})
@EnableScheduling
@EnableTransactionManagement
@MapperScan("com.example.lin.mapper")
public class LinApplication {
	private static Logger logger = LoggerFactory.getLogger(LinApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LinApplication.class, args);
		System.out.println("start up!");
		logger.debug("start up!");
	}
}
