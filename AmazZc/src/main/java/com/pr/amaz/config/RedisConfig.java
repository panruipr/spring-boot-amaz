package com.pr.amaz.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {



    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        //  设置自动key的生成规则，配置spring boot的注解，进行方法级别的缓存
        // 使用：进行分割，可以很多显示出层级关系
        // 这里其实就是new了一个KeyGenerator对象，只是这是lambda表达式的写法，我感觉很好用，大家感兴趣可以去了解下
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":");
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(":" + String.valueOf(obj));
            }
            String rsToUse = String.valueOf(sb);
            logger.info("自动生成Redis Key -> [{}]", rsToUse);
            return rsToUse;
        };
    }

    /**
     * 缓存管理机制
     *
     */
    @Bean
    @Override
    public CacheManager cacheManager(){
        logger.info("初始化缓存开始");
        return RedisCacheManager.create(redisConnectionFactory);
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //设置序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer); // key序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // value序列化
        redisTemplate.setHashKeySerializer(stringSerializer); // Hash key序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer); // Hash value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    /**
     * 此内部类是为了读取yml配置文件，创建JedisConnectionFactory和redisPool，用来给外部类初始化缓存器使用，否则外部类中不注入不了
     *
     */
    @ConfigurationProperties
    class DataJedisProperties{
        @Value("${spring.redis.host}")
        private String host;
        @Value("${spring.redis.port}")
        private int port;
        @Value("${spring.redis.password}")
        private String password;
        @Value("${spring.redis.jedis.pool.max-idle}")
        private int maxIdle;
        @Value("${spring.redis.jedis.pool.max-wait}")
        private int maxWait;
        @Value("${spring.redis.jedis.pool.max-active}")
        private int maxActive;
        @Value("${spring.redis.jedis.pool.min-idle}")
        private int minIdle;

        /**
         * 在2.0版本中JedisConnectionFactory中的setHostName已经过时，用RedisStandaloneConfiguration来创建
         * @return
         */
        @Bean
        public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig){

            logger.info("创建redis连接工厂host:" + host +"port:" + port +"password:" +RedisPassword.of(password).toString());
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            redisStandaloneConfiguration.setHostName(host);
            redisStandaloneConfiguration.setPort(port);
            //用RedisPassword创建一个password
            redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
            //我们用JedisClientConfiguration接口的builder方法实例化一个构造器，还得类型转换
            JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
            //修改我们的连接池配置
            logger.info("redis连接池" + jedisPoolConfig.getMaxWaitMillis() + jedisPoolConfig.toString());

            jpcf.poolConfig(jedisPoolConfig);
            //通过构造器来构造jedis客户端配置
            JedisClientConfiguration jedisClientConfiguration = jpcf.build();
            return  new JedisConnectionFactory(redisStandaloneConfiguration);
        }

        /**
         * 配置redis连接池相关信息
         */
        @Bean
        public JedisPoolConfig jedisPoolConfig(){
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            //最大连接数
            jedisPoolConfig.setMaxTotal(maxActive);
            //最小空闲连接数
            jedisPoolConfig.setMinIdle(minIdle);
            jedisPoolConfig.setMaxIdle(maxIdle);
            //最大空闲时间
            jedisPoolConfig.setMaxWaitMillis(maxWait);
            return jedisPoolConfig;
        }

    }


}
