package com.ta2khu75.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.model.request.ExamCategoryRequest;
import com.ta2khu75.quiz.model.response.ExamCategoryResponse;
import com.ta2khu75.quiz.service.ExamCategoryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/exam-category")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamCategoryController {
	ExamCategoryService examCategoryService;

	@GetMapping
	public ResponseEntity<List<ExamCategoryResponse>> readAllExamCategory() {
		return ResponseEntity.ok(examCategoryService.readAll());
	}

	@PostMapping
	public ResponseEntity<ExamCategoryResponse> createExamCategory(@RequestBody ExamCategoryRequest request) {
		return ResponseEntity.ok(examCategoryService.create(request));
	}

	@PutMapping("{id}")
	public ResponseEntity<ExamCategoryResponse> updateExamCategory(@PathVariable("id") Long id,
			@RequestBody ExamCategoryRequest request) {
		return ResponseEntity.ok(examCategoryService.update(id, request));
	}
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteExamCategory(@PathVariable("id") Long id) {
		examCategoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
