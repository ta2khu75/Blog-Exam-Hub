package com.ta2khu75.quiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.ta2khu75.quiz.model.request.AnswerRequest;
import com.ta2khu75.quiz.model.response.AnswerResponse;
import com.ta2khu75.quiz.service.AnswerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/answer")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerController {
	AnswerService service;

	@PostMapping
	public ResponseEntity<AnswerResponse> createAnswer(@Valid @RequestBody AnswerRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteAnswer(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("quiz/{id}")
	public ResponseEntity<List<AnswerResponse>> readAllAnswerQuiz(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.readAllByQuizId(id));
	}

	@PutMapping("{id}")
	public ResponseEntity<AnswerResponse> updateAnswer(@PathVariable("id") Long id,
			@Valid @RequestBody AnswerRequest request) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, request));
	}

	@GetMapping("{id}")
	public ResponseEntity<AnswerResponse> readAnswer(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.read(id));
	}
}
