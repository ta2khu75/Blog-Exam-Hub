package com.ta2khu75.quiz.service.impl;

public abstract class BaseServiceImpl<Repository, Mapper> {
	protected BaseServiceImpl(Repository repository, Mapper mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}
	protected final Repository repository;
	protected final Mapper mapper;
}
