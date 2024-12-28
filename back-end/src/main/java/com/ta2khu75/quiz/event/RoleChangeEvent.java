package com.ta2khu75.quiz.event;

import org.springframework.context.ApplicationEvent;

import com.ta2khu75.quiz.model.entity.Role;

import lombok.Getter;
@Getter
public class RoleChangeEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	private Role role;
	public RoleChangeEvent(Object source, Role role) {
		super(source);
		this.role = role;
	}

}
