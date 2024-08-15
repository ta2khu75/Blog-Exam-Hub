package com.ta2khu75.quiz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.entity.request.UserAnswerRequest;
import com.ta2khu75.quiz.entity.response.ExamHistoryResponse;
import com.ta2khu75.quiz.entity.response.details.ExamHistoryDetailsResponse;
import com.ta2khu75.quiz.repository.ExamRepository;
import com.ta2khu75.quiz.service.ExamHistoryService;
import com.ta2khu75.quiz.service.ProfessionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/exam-history")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamHistoryController {
	ExamHistoryService service;
	@GetMapping("exam/{id}")
	public ResponseEntity<ExamHistoryResponse> getMethodName(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.readByExamId(id));
	}
	@PostMapping("{id}")	
	public ResponseEntity<ExamHistoryDetailsResponse> getname(@PathVariable("id") Long examHistoryId, @RequestParam("exam_id") Long examId, @RequestBody UserAnswerRequest[] answerUserRequest) {
		return ResponseEntity.ok(service.scoreByExamId(examHistoryId, examId, answerUserRequest));
	}
}
