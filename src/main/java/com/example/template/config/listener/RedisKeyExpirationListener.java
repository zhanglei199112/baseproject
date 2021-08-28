package com.example.template.config.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

  public volatile static CopyOnWriteArrayList<String> existKey = new CopyOnWriteArrayList<>();
  public static AtomicLong atomicLong = new AtomicLong(0);

  public static AtomicBoolean print = new AtomicBoolean(false);

  public volatile static CopyOnWriteArrayList<String> allKey = new CopyOnWriteArrayList<>();

  public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
    super(listenerContainer);
  }

  @Override
  public void onMessage(Message message, byte[] pattern) {
    String expiredKey = message.toString();
    if(expiredKey.startsWith("10_")){
      atomicLong.getAndIncrement();
      if (print.get())  {
        System.out.println(expiredKey);
      }
      existKey.add(expiredKey);
    }
  }
}
