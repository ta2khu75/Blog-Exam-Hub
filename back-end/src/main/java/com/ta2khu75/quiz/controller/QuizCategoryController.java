package com.ta2khu75.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.model.request.QuizCategoryRequest;
import com.ta2khu75.quiz.model.response.QuizCategoryResponse;
import com.ta2khu75.quiz.service.QuizCategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("${app.api-prefix}/quiz-categories")
public class QuizCategoryController extends BaseController<QuizCategoryService>
		implements CrudController<QuizCategoryRequest, QuizCategoryResponse, Long> {

	public QuizCategoryController(QuizCategoryService service) {
		super(service);
	}

	@GetMapping
	@EndpointMapping(name = "Read all quiz category")
	public ResponseEntity<List<QuizCategoryResponse>> readAll() {
		return ResponseEntity.ok(service.readAll());
	}

	@Override
	@EndpointMapping(name = "Create quiz category")
	public ResponseEntity<QuizCategoryResponse> create(@Valid @RequestBody QuizCategoryRequest request) {
		return ResponseEntity.ok(service.create(request));
	}

	@Override
	@EndpointMapping(name = "Update quiz category")
	public ResponseEntity<QuizCategoryResponse> update(@PathVariable Long id,
			@Valid @RequestBody QuizCategoryRequest request) {
		return ResponseEntity.ok(service.update(id, request));
	}

	@Override
	@EndpointMapping(name = "Delete quiz category")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<QuizCategoryResponse> read(@PathVariable Long id) {
		return ResponseEntity.ok(service.read(id));
	}

}
