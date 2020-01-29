package com.test;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

@Service
public class RedisTestUtils {

    @Resource
    RedisTemplate redisTemplate;

    public void set(String key,String value){
        if (redisTemplate == null){
            System.out.println("redisTemplate is null");
        }
        redisTemplate.opsForValue().set(key,value);
    }

    public void showRedisStatus(){
        System.out.println("redis start.....");
    }
}
