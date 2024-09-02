package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.quiz.entity.PermissionGroup;
import com.ta2khu75.quiz.entity.response.PermissionGroupResponse;

@Mapper(componentModel = "spring")
public interface PermissionGroupMapper {

	PermissionGroupResponse toResponse(PermissionGroup request);
}
