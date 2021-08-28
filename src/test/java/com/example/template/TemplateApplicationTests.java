package com.example.template;

import com.example.template.config.listener.RedisKeyExpirationListener;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class TemplateApplicationTests {

  @Autowired
  RedisTemplate<String,String> redisTemplate;

  @Test
  void contextLoads() throws InterruptedException {

    //循环创建500个key,过期时间为5秒钟
    for(int i=0;i<500;i++){
      String substring = UUID.randomUUID().toString().substring(0, 5);
      substring = "10_"+substring;
      RedisKeyExpirationListener.allKey.add(substring);
      redisTemplate.opsForValue().set(substring,substring,10, TimeUnit.SECONDS);
    }

    //循环创建500个key,过期时间为5秒钟
    new Thread(()->{
      while (true){
        for (int i=0;i<500;i++){
          String substring = UUID.randomUUID().toString().substring(0, 5);
          substring = "5_"+substring;
          redisTemplate.opsForValue().set(substring,substring,5, TimeUnit.SECONDS);
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();

    Thread.sleep(20000);
    RedisKeyExpirationListener.print.set(true);
    //将两个之间的差值取出
    System.out.println(RedisKeyExpirationListener.atomicLong.get());
    if(RedisKeyExpirationListener.allKey.size()>0){
      String s = RedisKeyExpirationListener.allKey.get(0);
      redisTemplate.opsForValue().get(s);
    }

    Thread.sleep(100000000000l);

  }

}
