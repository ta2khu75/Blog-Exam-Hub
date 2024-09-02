package com.ta2khu75.quiz.entity.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
	@NotBlank
	String name;
	@NotEmpty
	List<Long> permissionIds;
}
