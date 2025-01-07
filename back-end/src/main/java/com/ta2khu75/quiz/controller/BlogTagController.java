package com.ta2khu75.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.model.entity.BlogTag;
import com.ta2khu75.quiz.service.BlogTagService;

@RestController
@RequestMapping("${app.api-prefix}/blog-tags")
public class BlogTagController extends BaseController<BlogTagService> {

	public BlogTagController(BlogTagService service) {
		super(service);
	}

	@GetMapping
	@EndpointMapping(name = "Read all blog tags")
	public ResponseEntity<List<BlogTag>> readAll() {
		return ResponseEntity.ok(service.readAll());
	}
}
