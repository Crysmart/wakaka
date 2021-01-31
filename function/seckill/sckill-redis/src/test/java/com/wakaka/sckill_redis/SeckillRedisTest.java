/*
 * @Author: your name
 * @Date: 2021-01-31 20:21:44
 * @LastEditTime: 2021-01-31 21:12:56
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \sckill-redis\src\test\java\com\wakaka\sckill_redis\SeckillRedisTest.java
 */
package com.wakaka.sckill_redis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillRedisTest {

    DefaultRedisScript script;

    @Before
    public void init() {
        script = new DefaultRedisScript();
        script.setScriptSource(new ResourceScriptSource(
                new ClassPathResource("asdasdasd.lua")
        ));
        script.setResultType(Integer.class);
    }

    @Autowired
    StringRedisTemplate redisTemplate;

    static final String goodsId = "seckill:goods:%s";

    String getKey(String id) {
        return String.format(goodsId, id);
    }

    public void prepare(String id, int total) {
        String key = getKey(id);
        if (redisTemplate.hasKey(key)) {
            return;
        }
        Map<String, String> goods = new HashMap<>();
        goods.put("total", String.valueOf(total));
        goods.put("start", "0");
        goods.put("alloc", "0");
        redisTemplate.opsForHash().putAll(key, goods);
    }

    public int secKill(String id, int number) {
        String key = getKey(id);
        Object alloc =  redisTemplate.execute(script, Arrays.asList(key), String.valueOf(number));
        return Integer.valueOf(alloc.toString());
    }

    @Test
    public void testSeckill() {
        String id = "114";
        prepare(id, 100);
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                int alloc = secKill(id, 1);
                System.out.println("count==================" + alloc);
            });
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new IllegalStateException();
        }

        executor.shutdown();
    }

    @Test
    public void testLua() {
        Object s = redisTemplate.execute(script,Arrays.asList("testtttt","666"),"666");
        System.out.println(s);
    }
}