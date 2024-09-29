package com.ta2khu75.quiz.service;

import java.util.List;

import com.ta2khu75.quiz.model.entity.BlogTag;

public interface BlogTagService {
	BlogTag create(String name);

	List<BlogTag> readAll();
}
