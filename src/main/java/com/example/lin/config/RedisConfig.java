//package com.example.demo.config;
//
//import org.apache.commons.pool.impl.GenericObjectPool;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import redis.clients.jedis.JedisPoolConfig;
//
//
///**
// * 时光之里山南水北,你我之间人山人海
// * <p>
// *
// * @author 折骨为刀
// * @create 2019-03-07 下午 02:14
// **/
//@Configuration
//@EnableCaching
//@EnableRedisRepositories
//@PropertySource(value = {"classpath:/application.properties",
//        "file:/C:\\scooper\\hti\\config.properties",
//        "file:/icooper/config/hti/config.properties"},
//        ignoreResourceNotFound = true)
//
//public class RedisConfig extends CachingConfigurerSupport {
////    private final CacheExpireConfig cacheExpireConfig;
//
//    @Value("${redis.ip}")
//    private String host;
//    @Value("${redis.port}")
//    private int port;
//    @Value("${redis.pass}")
//    private String password;
//
//    @Value("${redis.max.idle}")
//    private int maxIdle;
//    @Value("${redis.max.active}")
//    private int maxActive;
//    @Value("${redis.max.wait}")
//    private int maxWait;
//    @Value("${redis.test.on.borrow}")
//    private boolean testOnBorrow;
//
//    @Bean
//    public JedisPoolConfig jedisPoolConfig(){
//        GenericObjectPool pool = new GenericObjectPool();
//        pool.setMinIdle(maxIdle);
//        pool.setMaxActive(maxActive);
//        pool.setMaxWait(maxWait);
//        pool.setTestOnBorrow(testOnBorrow);
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWaitMillis(maxWait);
//        jedisPoolConfig.setMaxTotal(maxActive);
//        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
//        return jedisPoolConfig;
//    }
//
//   /* @Bean
//    public RedisHttpSessionConfiguration redisHttpSessionConfiguration(){
//
//    }*/
//
//    @Bean
//    public RedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        //设置redis服务器的host或者ip地址
//        redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setPort(port);
//        //获得默认的连接池构造
//        //这里需要注意的是，edisConnectionFactoryJ对于Standalone模式的没有（RedisStandaloneConfiguration，JedisPoolConfig）的构造函数，对此
//        //我们用JedisClientConfiguration接口的builder方法实例化一个构造器，还得类型转换
//        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf =
//                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
//        //修改我们的连接池配置
//        jpcf.poolConfig(jedisPoolConfig);
//        //通过构造器来构造jedis客户端配置
//        JedisClientConfiguration jedisClientConfiguration = jpcf.build();
//
//        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
//    }
//
//    @Bean(name="stringRedisTemplate")
//    public StringRedisTemplate stringRedisTemplate(JedisPoolConfig jedisPoolConfig){
//        StringRedisTemplate redisTemplate = new StringRedisTemplate();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory(jedisPoolConfig));
//        return redisTemplate;
//    }
//
//    @Bean(name="redisTemplate")
//    public RedisTemplate redisTemplate(JedisPoolConfig jedisPoolConfig){
//        RedisTemplate redisTemplate = new RedisTemplate();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory(jedisPoolConfig));
//        return redisTemplate;
//    }
//
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        return RedisCacheManager.create(redisConnectionFactory);
//    }
//}
