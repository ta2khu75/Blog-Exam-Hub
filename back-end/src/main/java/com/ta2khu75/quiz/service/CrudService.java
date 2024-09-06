package com.ta2khu75.quiz.service;

import jakarta.validation.Valid;
public interface CrudService<D, T, E> {
	default E create(@Valid T request) {
		System.out.println("Create");
		return null;
	}

	default E update(D id, @Valid T request) {
		System.out.println("Create");
		return null;
	}

	E read(D id);

	void delete(D id);
}