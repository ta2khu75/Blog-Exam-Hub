package com.ta2khu75.quiz.service;

import jakarta.validation.Valid;
public interface BaseService<D, T, E> {
	E create(@Valid T request);
	E update(D id, @Valid T request);
	E read(D id);
	void delete(D id);
}