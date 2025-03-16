package com.ta2khu75.quiz.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.model.request.CommentRequest;
import com.ta2khu75.quiz.model.response.CommentResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${app.api-prefix}/comments")
public class CommentController extends BaseController<CommentService>
		implements CrudController<CommentRequest, CommentResponse, String> {
	public CommentController(CommentService service) {
		super(service);
	}

	@GetMapping("/blog/{blogId}")
	@EndpointMapping(name = "Read comment blog")
	public ResponseEntity<PageResponse<CommentResponse>> readPage(@PathVariable("blogId") String blogId,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Pageable pageable = PageRequest.of(page-1, size, Sort.by("createdAt").descending());
		return ResponseEntity.ok(service.readPageByBlogId(blogId, pageable));
	}

	@Override
	@EndpointMapping(name = "Comment blog")
	public ResponseEntity<CommentResponse> create(@Valid @RequestBody CommentRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
	}

	@Override
	@PreAuthorize("@ownerSecurity.isCommentOwner(#id)")
	@EndpointMapping(name = "Update comment")
	public ResponseEntity<CommentResponse> update(@PathVariable String id, @Valid @RequestBody CommentRequest request) {
		return ResponseEntity.ok(service.update(id, request));
	}

	@Override
	@PreAuthorize("@ownerSecurity.isCommentOwner(#id) or hasRole('ROOT')")
	@EndpointMapping(name = "Delete comment")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<CommentResponse> read(@PathVariable String id) {
		return ResponseEntity.ok(service.read(id));
	}
}
