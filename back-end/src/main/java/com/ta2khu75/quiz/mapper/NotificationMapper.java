package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ta2khu75.quiz.model.entity.Notification;
import com.ta2khu75.quiz.model.response.NotificationResponse;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
	@Mapping(target = "target", ignore = true)
	NotificationResponse toResponse(Notification entity);
}
