package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.quiz.entity.Role;
import com.ta2khu75.quiz.entity.request.RoleRequest;
import com.ta2khu75.quiz.entity.response.RoleResponse;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	@Mapping(target = "accounts", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "permissions", ignore = true)
	Role toEntity(RoleRequest request);
	RoleResponse toResponse(Role role);
	@Mapping(target = "accounts", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "permissions", ignore = true)
	void update(RoleRequest request, @MappingTarget Role role);
}
