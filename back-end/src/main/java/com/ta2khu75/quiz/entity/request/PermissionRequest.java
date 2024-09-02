package com.ta2khu75.quiz.entity.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class PermissionRequest {
	Long id;
}
