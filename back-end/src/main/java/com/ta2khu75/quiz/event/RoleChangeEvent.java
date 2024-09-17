package com.ta2khu75.quiz.event;

import java.time.Clock;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
@Getter
public class RoleChangeEvent extends ApplicationEvent {
	private Long roleId;
	public RoleChangeEvent(Object source, Long roleId) {
		super(source);
		this.roleId = roleId;
	}

}
