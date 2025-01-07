package com.ta2khu75.quiz.service.base;

public abstract class BaseService<Repository, Mapper> {
	protected BaseService(Repository repository, Mapper mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}
	protected final Repository repository;
	protected final Mapper mapper;
}
