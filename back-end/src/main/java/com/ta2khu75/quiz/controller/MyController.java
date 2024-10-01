package com.ta2khu75.quiz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.exception.UnAuthorizedException;
import com.ta2khu75.quiz.model.request.search.BlogSearchRequest;
import com.ta2khu75.quiz.model.request.search.ExamSearchRequest;
import com.ta2khu75.quiz.model.response.BlogResponse;
import com.ta2khu75.quiz.model.response.CountResponse;
import com.ta2khu75.quiz.model.response.ExamResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.service.BlogService;
import com.ta2khu75.quiz.service.ExamService;
import com.ta2khu75.quiz.util.SecurityUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/my")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MyController {
	BlogService blogService;
	ExamService examService;

	@GetMapping("blog")
	public ResponseEntity<PageResponse<BlogResponse>> searchMyBlog(
			@ModelAttribute BlogSearchRequest blogSearchRequest) {
		blogSearchRequest.setAccessModifier(null);
		blogSearchRequest.setAuthorId(null);
		blogSearchRequest.setAuthorEmail(SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new UnAuthorizedException("You must login first!")));
		return ResponseEntity.ok(blogService.searchBlog(blogSearchRequest));
	}

	@GetMapping("count-blog")
	public ResponseEntity<CountResponse> countMyBlog() {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new UnAuthorizedException("You must login first!"));
		return ResponseEntity.ok(new CountResponse(blogService.countByAuthorEmail(email)));
	}

	@GetMapping("count-exam")
	public ResponseEntity<CountResponse> countMyExam() {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new UnAuthorizedException("You must login first!"));
		return ResponseEntity.ok(new CountResponse(examService.countByAuthorEmail(email)));
	}

	@GetMapping("exam")
	public ResponseEntity<PageResponse<ExamResponse>> searchMyExam(ExamSearchRequest examSearchRequest) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new UnAuthorizedException("You must login first!"));
		examSearchRequest.setAuthorEmail(email);
		examSearchRequest.setAccessModifier(null);
		examSearchRequest.setAuthorId(null);
		return ResponseEntity.ok(examService.searchExam(examSearchRequest));
	}
}
