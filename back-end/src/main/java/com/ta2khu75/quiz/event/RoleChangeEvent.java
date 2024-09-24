package com.ta2khu75.quiz.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
@Getter
public class RoleChangeEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	private Long roleId;
	public RoleChangeEvent(Object source, Long roleId) {
		super(source);
		this.roleId = roleId;
	}

}
