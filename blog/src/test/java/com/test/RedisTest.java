package com.test;

import com.blog.MyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@SpringBootTest(classes = MyApplication.class)
//@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void redisTest(){
        redisTemplate.opsForValue().set("111","fengzhi");
//        redisTestUtils.set("1111","fengzhi");
        System.out.println("reids test");
//        redisTestUtils.showRedisStatus();
    }
}
