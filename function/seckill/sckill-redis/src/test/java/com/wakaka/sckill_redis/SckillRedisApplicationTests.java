package com.wakaka.sckill_redis;

import com.wakaka.sckill_redis.entity.User;
import com.wakaka.sckill_redis.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
class SckillRedisApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Autowired
	StringRedisTemplate redisTemplate;

	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("test","test");
		String test = redisTemplate.opsForValue().get("test");
		System.out.println(test);
	}

}
