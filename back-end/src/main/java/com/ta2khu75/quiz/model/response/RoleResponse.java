package com.ta2khu75.quiz.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class RoleResponse {
	Long id;
	String name;
}
