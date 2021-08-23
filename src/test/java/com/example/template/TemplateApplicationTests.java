package com.example.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class TemplateApplicationTests {

  @Autowired
  RedisTemplate<String,String> redisTemplate;

  @Test
  void contextLoads() {
    redisTemplate.opsForValue().set("mykey", "xz");
    System.out.println(redisTemplate.opsForValue().get("mykey"));
  }

}
