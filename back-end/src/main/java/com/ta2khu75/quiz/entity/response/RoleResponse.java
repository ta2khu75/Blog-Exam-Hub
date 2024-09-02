package com.ta2khu75.quiz.entity.response;

import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {
	Long id;
	String name;
	Set<PermissionResponse> permissions;

}
