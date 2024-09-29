package com.ta2khu75.quiz.controller;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.model.request.UserAnswerRequest;
import com.ta2khu75.quiz.model.response.ExamResultResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.ExamResultDetailsResponse;
import com.ta2khu75.quiz.service.ExamResultService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/exam-result")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamResultController {
	ExamResultService service;
	@GetMapping("exam/{id}")
	public ResponseEntity<ExamResultResponse> takeExam(@PathVariable("id") String id) {
		ExamResultResponse response = service.readByExamId(id);
		if (response == null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.createByExamId(id));
		}
		return ResponseEntity.ok(service.readByExamId(id));
	}
	@PostMapping("{id}")	
	public ResponseEntity<ExamResultDetailsResponse> submitTheExam(@PathVariable("id") Long examHistoryId, @RequestBody UserAnswerRequest[] answerUserRequest) {
		return ResponseEntity.ok(service.scoreByExamId(examHistoryId, answerUserRequest));
	}
	@GetMapping("/page")
	public ResponseEntity<PageResponse<ExamResultResponse>> readPageExamResult(@RequestParam(name="size", required = false, defaultValue = "5") int size, @RequestParam(name="page", required = false, defaultValue = "0") int page) {
		Sort sort = Sort.by(Sort.Direction.DESC, "lastModifiedDate");
		Pageable pageable = PageRequest.of(page, size, sort);
		return ResponseEntity.ok(service.readPage(pageable));
	}
	@GetMapping("{id}")
	public ResponseEntity<ExamResultDetailsResponse> readExamResult(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.read(id));
	}
}
