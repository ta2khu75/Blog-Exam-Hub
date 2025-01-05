package com.ta2khu75.quiz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.validation.Valid;

public abstract class BaseController<Request, Response, Id, Service> {
	protected Service service;

	protected BaseController(Service service) {
		super();
		this.service = service;
	}

	@PostMapping
	abstract ResponseEntity<Response> create(@Valid Request request);

	@PutMapping("{id}")
	abstract ResponseEntity<Response> update(Id id, @Valid Request request);

	@DeleteMapping("{id}")
	abstract ResponseEntity<Void> delete(Id id);

	@GetMapping("{id}")
	abstract ResponseEntity<Response> read(Id id);

}
