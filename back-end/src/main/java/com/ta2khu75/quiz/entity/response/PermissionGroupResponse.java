package com.ta2khu75.quiz.entity.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class PermissionGroupResponse {
	String name;
	List<PermissionResponse> permissions;
}
