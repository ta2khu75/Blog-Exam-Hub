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

	@GetMapping("quiz/{quizId}")
	@EndpointMapping(name="Take quiz")
	public ResponseEntity<QuizResultResponse> create(@PathVariable String quizId) {
		QuizResultResponse response = service.read(quizId);
		if (response == null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.create(quizId));
		}
		return ResponseEntity.ok(response);
	}

	@PutMapping("{id}")
	@EndpointMapping(name="Submit quiz")
	public ResponseEntity<QuizResultResponse> update(@PathVariable String id,
			@RequestBody QuizResultRequest quizResultRequest) {
		return ResponseEntity.ok(service.update(id, quizResultRequest));
	}

	@GetMapping
	@EndpointMapping(name="Search quiz result")
	public ResponseEntity<PageResponse<QuizResultResponse>> readPage(QuizResultSearch searchRequest ) {
		return ResponseEntity.ok(service.search(searchRequest));
	}

	@GetMapping("{id}")
	@EndpointMapping(name="Read quiz result")
	public ResponseEntity<QuizResultResponse> readDetail(@PathVariable String id) {
		return ResponseEntity.ok(service.readDetail(id));
	}
}
