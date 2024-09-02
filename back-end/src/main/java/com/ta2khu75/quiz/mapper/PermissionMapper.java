package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.quiz.entity.Permission;
import com.ta2khu75.quiz.entity.response.PermissionResponse;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

	PermissionResponse toResponse(Permission request);
}
