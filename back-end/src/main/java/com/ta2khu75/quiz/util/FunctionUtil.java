package com.ta2khu75.quiz.util;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.exception.UnAuthenticatedException;
import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.request.search.Search;

public class FunctionUtil {
	private FunctionUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static <T, K> T findOrThrow(K key, Class<T> clazz, Function<K, Optional<T>> findFunction) {
		return findFunction.apply(key).orElseThrow(
				() -> new NotFoundException("Could not found %s with key ".formatted(clazz.getSimpleName()) + key));
	}
	public static <T> void shuffleList(List<T> list) {
        Random rand = new Random();
        for (int i = list.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Hoán đổi phần tử tại i và j
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
    }
//	public static void setPublicIfNotOwner(Search search) {
//		try {
//			String accountId=SecurityUtil.getCurrentUserLogin();
//			if(!accountId.equals(search.getAuthorId())) {
//				search.setAccessModifier(AccessModifier.PUBLIC);
//			}
//		} catch (UnAuthenticatedException e) {
//			search.setAccessModifier(AccessModifier.PUBLIC);
//		}
//	}

}
