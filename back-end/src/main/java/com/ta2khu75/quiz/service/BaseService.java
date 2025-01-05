package com.ta2khu75.quiz.service;

import jakarta.validation.Valid;
public interface BaseService<Request, Response, Id> {
	Response create(@Valid Request request);
	Response update(Id id, @Valid Request request);
	Response read(Id id);
	void delete(Id id);
}