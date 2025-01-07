package com.ta2khu75.quiz.controller;

public abstract class BaseController<Service> {
	protected Service service;

	protected BaseController(Service service) {
		super();
		this.service = service;
	}
}
