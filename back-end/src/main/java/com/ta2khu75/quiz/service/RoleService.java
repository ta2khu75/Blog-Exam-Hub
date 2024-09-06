package com.ta2khu75.quiz.service;

import java.util.List;

import com.ta2khu75.quiz.model.request.RoleRequest;
import com.ta2khu75.quiz.model.response.details.RoleDetailsResponse;

public interface RoleService extends CrudService<Long, RoleRequest, RoleDetailsResponse> {
	List<RoleDetailsResponse> readAll();
}
