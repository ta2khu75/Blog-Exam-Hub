package com.ta2khu75.quiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.model.request.QuizResultRequest;
import com.ta2khu75.quiz.model.request.search.QuizResultSearch;
import com.ta2khu75.quiz.model.response.QuizResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.service.QuizResultService;

@RestController
@RequestMapping("${app.api-prefix}/quiz-results")
public class QuizResultController extends BaseController<QuizResultService> {
	public QuizResultController(QuizResultService service) {
		super(service);
	}

	@GetMapping("exam/{examId}")
	@EndpointMapping(name="Take exam")
	public ResponseEntity<QuizResultResponse> create(@PathVariable String examId) {
		QuizResultResponse response = service.read(examId);
		if (response == null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.create(examId));
		}
		return ResponseEntity.ok(response);
	}

	@PutMapping("{id}")
	@EndpointMapping(name="Submit exam")
	public ResponseEntity<QuizResultResponse> update(@PathVariable String id,
			@RequestBody QuizResultRequest examResultRequest) {
		return ResponseEntity.ok(service.update(id, examResultRequest));
	}

	@GetMapping
	@EndpointMapping(name="Search exam result")
	public ResponseEntity<PageResponse<QuizResultResponse>> readPage(QuizResultSearch searchRequest ) {
		return ResponseEntity.ok(service.search(searchRequest));
	}

	@GetMapping("{id}")
	@EndpointMapping(name="Read exam result")
	public ResponseEntity<QuizResultResponse> readDetail(@PathVariable String id) {
		return ResponseEntity.ok(service.readDetail(id));
	}
}
