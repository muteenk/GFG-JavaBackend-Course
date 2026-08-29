package com.schoolproject.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Map;

@Configuration
public class CacheConfig {

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        GenericJacksonJsonRedisSerializer serializer = GenericJacksonJsonRedisSerializer
                .builder().build();

        RedisCacheConfiguration defaultConfiguration = RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeValuesWith(
                    RedisSerializationContext.SerializationPair
                            .fromSerializer(serializer)
            );

        RedisCacheConfiguration studentListConfig =
                defaultConfiguration.entryTtl(Duration.ofMinutes(5));


        RedisCacheConfiguration studentConfig =
                defaultConfiguration.entryTtl(Duration.ofMinutes(30));


        Map<String, RedisCacheConfiguration> cacheConfigurations =
                Map.of(
                        "student-list", studentListConfig,
                        "students", studentConfig
                );


        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfiguration)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }



}
