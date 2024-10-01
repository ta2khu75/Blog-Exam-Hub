package com.ta2khu75.quiz.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta2khu75.quiz.exception.UnAuthorizedException;
import com.ta2khu75.quiz.model.AccessModifier;
import com.ta2khu75.quiz.model.request.BlogRequest;
import com.ta2khu75.quiz.model.request.search.BlogSearchRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.CountResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.BlogDetailsResponse;
import com.ta2khu75.quiz.service.BlogService;
import com.ta2khu75.quiz.util.SecurityUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/blog")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogController {
	BlogService service;
	ObjectMapper mapper;

	@GetMapping
	public ResponseEntity<PageResponse<BlogResponse>> searchBlog(@ModelAttribute // có hay khong co cung khong sao neu
																					// validation thì cần
	BlogSearchRequest blogSearchRequest) {
		blogSearchRequest.setAccessModifier(AccessModifier.PUBLIC);
		blogSearchRequest.setAuthorEmail(null);
		return ResponseEntity.ok(service.searchBlog(blogSearchRequest));
	}

	

	@GetMapping("/{id}")
	public ResponseEntity<BlogResponse> readBlog(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.read(id));
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<BlogDetailsResponse> readDetailsBlog(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.readDetail(id));
	}

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<BlogResponse> createBlog(@RequestPart("blog") String request,
			@RequestPart(name = "image", required = false) MultipartFile file) throws IOException {
		BlogRequest blogRequest = mapper.readValue(request, BlogRequest.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(blogRequest, file));
	}
	@PutMapping(path = "/{id}", consumes = "multipart/form-data")
	public ResponseEntity<BlogResponse> updateBlog(@PathVariable("id") String id, @RequestPart("blog") String request,
			@RequestPart(name = "image", required = false) MultipartFile file) throws IOException {
		BlogRequest blogRequest = mapper.readValue(request, BlogRequest.class);
		return ResponseEntity.ok(service.update(id, blogRequest, file));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BlogResponse> deleteBlog(@PathVariable("id") String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	@GetMapping("my-blog/count")
	public ResponseEntity<CountResponse> countMyBlog() {
		String email=SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new UnAuthorizedException("You must login first!"));
		return ResponseEntity.ok(new CountResponse(service.countByAuthorEmail(email)));
	}
	@GetMapping("my-blog")
	public ResponseEntity<PageResponse<BlogResponse>> searchMyBlog(
			@ModelAttribute BlogSearchRequest blogSearchRequest) {
		blogSearchRequest.setAccessModifier(null);
		blogSearchRequest.setAuthorId(null);
		blogSearchRequest.setAuthorEmail(SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new UnAuthorizedException("You must login first!")));
		return ResponseEntity.ok(service.searchBlog(blogSearchRequest));
	}
	@GetMapping("{authorId}/count")
	public ResponseEntity<CountResponse> countBlogAuthor(@PathVariable("authorId") String id) {
		return ResponseEntity.ok(new CountResponse(service.countByAuthorIdAndAccessModifier(id, AccessModifier.PUBLIC)));
	}

}
