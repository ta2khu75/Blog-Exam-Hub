package com.ta2khu75.quiz.service.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisUtil {
	private final RedisTemplate<String, Object> redisTemplate;
//	private final ObjectMapper objectMapper;

	public void create(NameModel nameModel, Object key, Object value) {
		redisTemplate.opsForHash().put(nameModel.value, key, value);
	}

	public <T> T read(NameModel nameModel, Object key, Class<T> clazz) {
		Object object = redisTemplate.opsForHash().get(nameModel.value, key);
		if (object == null) {
			return null;
		}
		return clazz.cast(object);
	}

	public void delete(NameModel nameModel, String key) {
		redisTemplate.opsForHash().delete(nameModel.getValue(), key);
	}

	public enum NameModel {
		ROLE("ROLE"), ACCOUNT("ACCOUNT");

		private final String value;

		// Constructor
		NameModel(String value) {
			this.value = value;
		}

		// Getter để lấy giá trị của enum
		public String getValue() {
			return value;
		}
	}
}