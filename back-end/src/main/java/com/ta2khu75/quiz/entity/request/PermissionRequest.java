package com.ta2khu75.quiz.entity.request;

import com.ta2khu75.quiz.entity.HTTPMethod;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class PermissionRequest {
	String name;
	HTTPMethod method;
}
