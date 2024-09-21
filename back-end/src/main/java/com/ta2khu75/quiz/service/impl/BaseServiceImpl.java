package com.ta2khu75.quiz.service.impl;

public abstract class BaseServiceImpl<Y , R> {
	public BaseServiceImpl(Y repository, R mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}
	protected final Y repository;
	protected final R mapper;
}
