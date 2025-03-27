package com.ta2khu75.quiz.service.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.constraints.NotNull;

@Service
@Slf4j
@RequiredArgsConstructor
@Validated
public class RedisUtil {
	private final RedisTemplate<String, Object> redisTemplate;
	private final ObjectMapper objectMapper;

	public void create(@NotNull NameModel nameModel, @NotNull Object key, @NotNull Object value) {
		try {
			redisTemplate.opsForHash().put(nameModel.value, key, value);
		} catch (SerializationException e) {
			String jsonValue;
			try {
				jsonValue = objectMapper.writeValueAsString(value);
				redisTemplate.opsForHash().put(nameModel.value, key, jsonValue);
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}
		}
	}

	public <T> T read(@NotNull NameModel nameModel, @NotNull Object key, @NotNull Class<T> clazz){
		Object object = redisTemplate.opsForHash().get(nameModel.value, key);
		if (object == null) {
			return null;
		}
		if (object instanceof String string) {
			try {
				return objectMapper.readValue(string, clazz);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return clazz.cast(object);
		}
	}

	public void delete(NameModel nameModel, String key) {
		redisTemplate.opsForHash().delete(nameModel.getValue(), key);
	}
	public enum NameModel {
		ROLE("ROLE"), ACCOUNT("ACCOUNT"), QUIZ_RESULT_RESPONSE("QUIZ_RESULT_RESPONSE");

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