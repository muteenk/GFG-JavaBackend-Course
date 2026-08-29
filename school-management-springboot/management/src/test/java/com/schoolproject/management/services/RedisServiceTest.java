package com.schoolproject.management.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @BeforeEach
    void beforeEach() {
        redisTemplate.opsForValue().set("my-mail", "test@gmail.com");
    }

    @Test
    void redisTest() {
        String s = redisTemplate.opsForValue().get("my-mail");
        System.out.println(s);
    }

}
