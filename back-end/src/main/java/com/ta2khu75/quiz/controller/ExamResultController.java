package com.ta2khu75.quiz.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.model.request.ExamResultRequest;
import com.ta2khu75.quiz.model.response.ExamResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamResultDetailResponse;
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
	public ResponseEntity<ExamResultDetailResponse> update(@PathVariable String id,
			@RequestBody ExamResultRequest examResultRequest) {
		return ResponseEntity.ok(service.scoreByExamId(id, examResultRequest));
	}

	@GetMapping("/page")
	@EndpointMapping(name="Read page exam result")
	public ResponseEntity<PageResponse<ExamResultResponse>> readPage(
			@RequestParam(required = false, defaultValue = "5") int size,
			@RequestParam(required = false, defaultValue = "0") int page) {
		Sort sort = Sort.by(Sort.Direction.DESC, "lastModifiedDate");
		Pageable pageable = PageRequest.of(page, size, sort);
		return ResponseEntity.ok(service.readPage(pageable));
	}

	@GetMapping("{id}")
	@EndpointMapping(name="Read exam result")
	public ResponseEntity<ExamResultDetailResponse> read(@PathVariable String id) {
		return ResponseEntity.ok(service.read(id));
	}
}
