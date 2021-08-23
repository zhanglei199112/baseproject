package com.example.template.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RedisController {

  private RedisTemplate<String,String> redisTemplate;

}
