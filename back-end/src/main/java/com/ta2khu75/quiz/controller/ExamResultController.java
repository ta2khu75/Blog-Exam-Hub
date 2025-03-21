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
import com.ta2khu75.quiz.model.request.ExamResultRequest;
import com.ta2khu75.quiz.model.request.search.ExamResultSearch;
import com.ta2khu75.quiz.model.response.ExamResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.service.ExamResultService;

@RestController
@RequestMapping("${app.api-prefix}/exam-results")
public class ExamResultController extends BaseController<ExamResultService> {
	public ExamResultController(ExamResultService service) {
		super(service);
	}

	@GetMapping("exam/{examId}")
	@EndpointMapping(name="Take exam")
	public ResponseEntity<ExamResultResponse> create(@PathVariable String examId) {
		ExamResultResponse response = service.readByExamId(examId);
		if (response == null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.createByExamId(examId));
		}
		return ResponseEntity.ok(response);
	}

	@PutMapping("{id}")
	@EndpointMapping(name="Submit exam")
	public ResponseEntity<ExamResultResponse> update(@PathVariable String id,
			@RequestBody ExamResultRequest examResultRequest) {
		return ResponseEntity.ok(service.scoreByExamId(id, examResultRequest));
	}

	@GetMapping
	@EndpointMapping(name="Search exam result")
	public ResponseEntity<PageResponse<ExamResultResponse>> readPage(ExamResultSearch searchRequest ) {
		return ResponseEntity.ok(service.search(searchRequest));
	}

	@GetMapping("{id}")
	@EndpointMapping(name="Read result exam result")
	public ResponseEntity<ExamResultResponse> readDetails(@PathVariable String id) {
		return ResponseEntity.ok(service.readDetails(id));
	}
}
