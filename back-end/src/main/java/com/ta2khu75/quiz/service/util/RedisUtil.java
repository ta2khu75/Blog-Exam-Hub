package com.ta2khu75.quiz.service.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisCallback;

@Service
@RequiredArgsConstructor
public class RedisUtil {
	private final RedisTemplate<String, Object> redisTemplate;
	private final ObjectMapper objectMapper;

	public <T> void create(String key, T value) {
		redisTemplate.execute((RedisCallback<Object>) connection -> {
			connection.hashCommands().hSet(value.getClass().getName().getBytes(StandardCharsets.UTF_8),
					key.getBytes(StandardCharsets.UTF_8), serializeUser(value));
			return null;
		});
	}

	public <T> T read(String key, Class<T> type) {
		return (T) redisTemplate.execute((RedisCallback<Object>) connection -> {
			byte[] value = connection.hashCommands().hGet(type.getName().getBytes(StandardCharsets.UTF_8),
					key.getBytes(StandardCharsets.UTF_8));

			if (value == null) {
				// Trả về null hoặc ném ra ngoại lệ tùy theo logic của bạn
				return null;
			}

			return deserializeUser(value, type);
		});
//		return (T) redisTemplate.execute((RedisCallback<Object>) connection -> {
//			byte[] value = connection.hashCommands().hGet(type.getName().getBytes(StandardCharsets.UTF_8),
//					key.getBytes(StandardCharsets.UTF_8));
//			return deserializeUser(value, type);
//		});
	}

	public <T> void delete(String key, Class<T> type) {
		redisTemplate.execute((RedisCallback<Object>) connection -> {
			connection.hashCommands().hDel(type.getName().getBytes(StandardCharsets.UTF_8),
					key.getBytes(StandardCharsets.UTF_8));
			return null;
		});
	}

	private <T> byte[] serializeUser(T value) {
		try {
			return objectMapper.writeValueAsBytes(value);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	};

	private <T> T deserializeUser(byte[] value, Class<T> type) {
		if (value == null || value.length == 0) {
			return null; // hoặc ném ra ngoại lệ nếu cần
		}
		try {
			return objectMapper.readValue(value, type);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}