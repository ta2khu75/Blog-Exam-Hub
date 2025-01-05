package com.ta2khu75.quiz.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.model.request.CommentRequest;
import com.ta2khu75.quiz.model.response.CommentResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${app.api-prefix}/comments")
public class CommentController extends BaseController<CommentRequest, CommentResponse, String, CommentService> {
	protected CommentController(CommentService service) {
		super(service);
	}

	@GetMapping("/blog/{blogId}")
	public ResponseEntity<PageResponse<CommentResponse>> readPageCommentBlog(@PathVariable("blogId") String blogId,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		return ResponseEntity.ok(service.readPageByBlogId(blogId, pageable));
	}

	@Override
	ResponseEntity<CommentResponse> create(@Valid @RequestBody CommentRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
	}

	@Override
	@PreAuthorize("@ownerSecurity.isCommentOwner(#id)")
	ResponseEntity<CommentResponse> update(@PathVariable String id, @Valid @RequestBody CommentRequest request) {
		return ResponseEntity.ok(service.update(id, request));
	}

	@Override
	@PreAuthorize("@ownerSecurity.isCommentOwner(#id) or hasRole('ROOT')")
	ResponseEntity<Void> delete(String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	ResponseEntity<CommentResponse> read(String id) {
		return ResponseEntity.ok(service.read(id));
	}
}
