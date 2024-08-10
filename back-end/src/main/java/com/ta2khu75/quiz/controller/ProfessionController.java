package com.ta2khu75.quiz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.entity.request.AnswerUserRequest;
import com.ta2khu75.quiz.service.ProfessionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${app.api-prefix}/profession")
public class ProfessionController {
	ProfessionService service;
	@PostMapping("/answer-user/{quizId}")
	public ResponseEntity<Double> getname(@PathVariable("quizId") Long quizId, @RequestBody AnswerUserRequest[] answerUserRequest) {
		double score = service.score(quizId, answerUserRequest);
		return ResponseEntity.ok(score);
	}
}
