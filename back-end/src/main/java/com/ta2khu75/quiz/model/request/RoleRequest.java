package com.ta2khu75.quiz.model.request;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {
	@NotBlank
	String name;
	Set<Long> permissionIds=new HashSet<>();
}
