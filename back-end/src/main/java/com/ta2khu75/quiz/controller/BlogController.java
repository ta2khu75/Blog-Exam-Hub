package com.ta2khu75.quiz.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta2khu75.quiz.model.request.BlogRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.service.BlogService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/blog")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogController{
	BlogService service;
	ObjectMapper mapper;
	@GetMapping("/{id}")
	public ResponseEntity<BlogResponse> readBlog(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.read(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BlogResponse> deleteBlog(@PathVariable("id") String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<BlogResponse> createBlog(@RequestPart("blog") String request,
			@RequestPart("image") MultipartFile file) throws IOException {
		BlogRequest blogRequest = mapper.readValue(request, BlogRequest.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(blogRequest, file));
	}

	@PutMapping(path = "/{id}", consumes = "multipart/form-data")
	public ResponseEntity<BlogResponse> updateBlog(String id, @RequestPart("blog") String request,
			@RequestPart("image") MultipartFile file) throws IOException {
		BlogRequest blogRequest = mapper.readValue(request, BlogRequest.class);
		return ResponseEntity.ok(service.update(id, blogRequest, file));
	}
}
