package com.ta2khu75.quiz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.model.entity.BlogTag;
import com.ta2khu75.quiz.repository.BlogTagRepository;
import com.ta2khu75.quiz.service.BlogTagService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogTagServiceImpl implements BlogTagService {
	BlogTagRepository blogTagRepository;
	@Override
	public BlogTag create(String name) {
		BlogTag blog = new BlogTag();
		blog.setName(name);
		return blogTagRepository.save(blog);
	}
	@Override
	public List<BlogTag> readAll() {
		return blogTagRepository.findAll();
	}
}
