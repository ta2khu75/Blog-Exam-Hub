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
import com.ta2khu75.quiz.model.request.CommentRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.CommentResponse;
import com.ta2khu75.quiz.service.CommentService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/comment")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {
	CommentService service;
	ObjectMapper mapper;

	@GetMapping("/{id}")
	public ResponseEntity<CommentResponse> readComment(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.read(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BlogResponse> deleteBlog(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<CommentResponse> createBlog(@RequestPart("comment") String request,
			@RequestPart("file") MultipartFile file) throws IOException {
		CommentRequest commentRequest = mapper.readValue(request, CommentRequest.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(commentRequest, file));
	}

	@PutMapping(path = "/{id}", consumes = "multipart/form-data")
	public ResponseEntity<CommentResponse> updateBlog(Long id, @RequestPart("comment") String request,
			@RequestPart("image") MultipartFile file) throws IOException {
		CommentRequest commentRequest = mapper.readValue(request, CommentRequest.class);
		return ResponseEntity.ok(service.update(id, commentRequest, file));
	}
}
