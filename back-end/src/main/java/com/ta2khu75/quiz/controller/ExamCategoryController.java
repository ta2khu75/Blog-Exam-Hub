package com.ta2khu75.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.model.request.ExamCategoryRequest;
import com.ta2khu75.quiz.model.response.ExamCategoryResponse;
import com.ta2khu75.quiz.service.ExamCategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("${app.api-prefix}/exam-categories")
public class ExamCategoryController
		extends BaseController<ExamCategoryRequest, ExamCategoryResponse, Long, ExamCategoryService> {

	protected ExamCategoryController(ExamCategoryService service) {
		super(service);
	}

	@GetMapping
	public ResponseEntity<List<ExamCategoryResponse>> readAllExamCategory() {
		return ResponseEntity.ok(service.readAll());
	}

	@Override
	ResponseEntity<ExamCategoryResponse> create(@Valid @RequestBody ExamCategoryRequest request) {
		return ResponseEntity.ok(service.create(request));
	}

	@Override
	ResponseEntity<ExamCategoryResponse> update(@PathVariable Long id,
			@Valid @RequestBody ExamCategoryRequest request) {
		return ResponseEntity.ok(service.update(id, request));
	}

	@Override
	ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	ResponseEntity<ExamCategoryResponse> read(@PathVariable Long id) {
		return ResponseEntity.ok(service.read(id));
	}

}
