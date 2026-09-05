package com.splitwise.splitwise.services.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T get(String key, Class<T> entityClass) {
        Object valueObject = redisTemplate.opsForValue().get(key);
        if (valueObject == null) return null;
        return objectMapper.readValue(valueObject.toString(), entityClass);
    }

    public <T> T get(String key, TypeReference<T> typeReference) {
        Object valueObject = redisTemplate.opsForValue().get(key);
        if (valueObject == null) return null;
        return objectMapper.readValue(valueObject.toString(), typeReference);
    }

    public void set(String key, Object value) {
        String valueString = objectMapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, valueString);
    }

    public void set(String key, Object value, Long ttl) {
        String valueString = objectMapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, valueString, ttl, TimeUnit.SECONDS);
    }

    public String remove(String key) {
        return redisTemplate.opsForValue().getAndDelete(key);
    }
}
