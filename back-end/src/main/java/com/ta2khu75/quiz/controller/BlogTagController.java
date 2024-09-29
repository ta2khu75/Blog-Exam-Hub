package com.ta2khu75.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.model.entity.BlogTag;
import com.ta2khu75.quiz.service.BlogTagService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("${app.api-prefix}/blog-tag")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal  = true)
public class BlogTagController {
	BlogTagService blogTagService;
	@GetMapping
	public ResponseEntity<List<BlogTag>> readAllBlogTags() {
		return ResponseEntity.ok(blogTagService.readAll());
	}
}
