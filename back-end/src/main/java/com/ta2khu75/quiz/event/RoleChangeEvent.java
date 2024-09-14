package com.ta2khu75.quiz.event;

import org.springframework.context.ApplicationEvent;

public class RoleChangeEvent extends ApplicationEvent {

	public RoleChangeEvent(Object source) {
		super(source);
	}

}
