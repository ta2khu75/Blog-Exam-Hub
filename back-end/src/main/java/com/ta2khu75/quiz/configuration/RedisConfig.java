package com.ta2khu75.quiz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

	@Bean
	RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
//	    JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
//        template.setKeySerializer(serializer);
//        template.setValueSerializer(serializer);
		return template;
	}
}