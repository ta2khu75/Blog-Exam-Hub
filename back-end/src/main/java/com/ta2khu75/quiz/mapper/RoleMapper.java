package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;

import com.ta2khu75.quiz.model.request.RoleRequest;
import com.ta2khu75.quiz.model.response.RoleResponse;
import com.ta2khu75.quiz.model.response.details.RoleDetailsResponse;
import com.ta2khu75.quiz.model.entity.Permission;
import com.ta2khu75.quiz.model.entity.Role;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	@Mapping(target = "accounts", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "permissions", ignore = true)
	Role toEntity(RoleRequest request);
	RoleResponse toResponse(Role role);
	@Mapping(target = "permissionIds", source = "permissions")
	RoleDetailsResponse toDetailsResponse(Role role);
	@Mapping(target = "accounts", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "permissions", ignore = true)
	void update(RoleRequest request, @MappingTarget Role role);
	default Long map(Permission permission) {	
		if(permission == null) {
			return null;
		}
		return permission.getId();
	}
}
