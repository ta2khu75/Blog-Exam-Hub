package com.ta2khu75.quiz.service.base;

import jakarta.validation.Valid;
public interface CrudService<REQ, RES, Id> {
	public RES create(@Valid REQ request);
	RES update(Id id, @Valid REQ request);
	RES read(Id id);
	void delete(Id id);
}