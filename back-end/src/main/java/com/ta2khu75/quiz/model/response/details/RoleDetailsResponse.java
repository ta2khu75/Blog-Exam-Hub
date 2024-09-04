package com.ta2khu75.quiz.model.response.details;

import java.util.Set;

import com.ta2khu75.quiz.model.response.RoleResponse;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDetailsResponse extends RoleResponse {
	Set<Long> permissionIds;
}
