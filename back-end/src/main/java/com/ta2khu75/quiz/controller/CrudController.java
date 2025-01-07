package com.ta2khu75.quiz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.validation.Valid;

public interface CrudController<Request, Response, Id> {
	@PostMapping
	ResponseEntity<Response> create(@Valid Request request);

	@PutMapping("{id}")
	ResponseEntity<Response> update(Id id, @Valid Request request);

	@DeleteMapping("{id}")
	ResponseEntity<Void> delete(Id id);

	@GetMapping("{id}")
	ResponseEntity<Response> read(Id id);
}
