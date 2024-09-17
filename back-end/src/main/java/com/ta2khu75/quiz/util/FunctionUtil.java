package com.ta2khu75.quiz.util;

import java.util.Optional;
import java.util.function.Function;

import com.ta2khu75.quiz.exception.NotFoundException;

public class FunctionUtil {
	private FunctionUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static <T, K> T findOrThrow(K key, Class<T> clazz, Function<K, Optional<T>> findFunction) {
		return findFunction.apply(key).orElseThrow(
				() -> new NotFoundException("Could not found %s with key ".formatted(clazz.getSimpleName()) + key));
	}
}
