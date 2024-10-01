package com.ta2khu75.quiz.controller;

import java.io.IOException;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta2khu75.quiz.model.request.CommentRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.CommentResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
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
	@GetMapping("/blog/{id}")
	public ResponseEntity<PageResponse<CommentResponse>> readPageCommentBlog(@PathVariable("id") String id, @RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page	) {
		Pageable pageable = Pageable.ofSize(size).withPage(page-1);
		return ResponseEntity.ok(service.readPageByBlogId(id, pageable));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BlogResponse> deleteComment(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<CommentResponse> createComment(@RequestPart("comment") String request,
			@RequestPart(name = "image", required = false) MultipartFile file) throws IOException {
		CommentRequest commentRequest = mapper.readValue(request, CommentRequest.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(commentRequest, file));
	}

	@PutMapping(path = "/{id}", consumes = "multipart/form-data")
	public ResponseEntity<CommentResponse> updateComment(Long id, @RequestPart("comment") String request,
			@RequestPart(name = "image", required = false) MultipartFile file) throws IOException {
		CommentRequest commentRequest = mapper.readValue(request, CommentRequest.class);
		return ResponseEntity.ok(service.update(id, commentRequest, file));
	}
}
