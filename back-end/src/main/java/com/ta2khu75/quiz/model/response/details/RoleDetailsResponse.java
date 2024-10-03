package com.ta2khu75.quiz.model.response.details;

import java.util.Set;

import com.ta2khu75.quiz.model.response.RoleResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDetailsResponse extends RoleResponse {
	private Set<Long> permissionIds;
}
