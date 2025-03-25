package com.ta2khu75.quiz.service.base;

import com.ta2khu75.quiz.model.request.search.Search;
import com.ta2khu75.quiz.model.response.PageResponse;

public interface SearchService< T,S extends Search> {
	PageResponse<T> search(S search);
}
