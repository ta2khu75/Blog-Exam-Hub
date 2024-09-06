package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.quiz.model.response.PermissionResponse;
import com.ta2khu75.quiz.model.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

	PermissionResponse toResponse(Permission request);
}
