package com.ta2khu75.quiz.service.impl;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public abstract class BaseServiceImpl<Repository, Mapper> {
	protected final Repository repository;
	protected final Mapper mapper;
}
