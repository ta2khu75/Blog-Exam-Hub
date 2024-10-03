package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.quiz.model.response.PermissionGroupResponse;
import com.ta2khu75.quiz.model.entity.PermissionGroup;

@Mapper(componentModel = "spring")
public interface PermissionGroupMapper {
	PermissionGroupResponse toResponse(PermissionGroup request);
}
