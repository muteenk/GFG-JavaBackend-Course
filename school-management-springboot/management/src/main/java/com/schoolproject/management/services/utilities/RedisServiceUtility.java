package com.schoolproject.management.services.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceUtility {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> void set(String key, T value) {
        String stringValue = objectMapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, stringValue);
    }

    public void set(String key, Object value, Long ttl) {
        String stringValue = objectMapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, stringValue, ttl, TimeUnit.SECONDS);
    }

    public <T> T get(String key, Class<T> entityClass) {
        String stringValue = redisTemplate.opsForValue().get(key);
        if (stringValue == null) return null;
        return objectMapper.readValue(stringValue, entityClass);
    }

    public <T> T get(String key, TypeReference<T> typeReference) {
        String stringValue = redisTemplate.opsForValue().get(key);
        if (stringValue == null) return null;
        return objectMapper.readValue(stringValue, typeReference);
    }

    public String remove(String key) {
        return redisTemplate.opsForValue().getAndDelete(key);
    }

}
