package com.tambola.app.utils.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@PropertySource("db.properties")
public class RedisConfiguration{

  @Value("${redis.hostname}")
  private String redisHostName;
  
  @Value("${redis.port}")
  private int redisPort;
  
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
    return new PropertySourcesPlaceholderConfigurer();
  }
  
  @Bean
  JedisConnectionFactory jedisConnectionFactory(){
    JedisConnectionFactory factory = new JedisConnectionFactory();
    factory.setHstName(redisHostName);
    factory.setPort(redisPort);
    factory.usePool(true);
    factory.getPoolConfig().setMaxIdle(30);
    factory.getPoolConfig().setMinIdle(10);
    return factory;
  }
  
  @Bean
  RedisTemplate<Object, Object> redisTemplate(){
    RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }
}
