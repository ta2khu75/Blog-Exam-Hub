package com.ta2khu75.quiz.service;

import java.util.List;

import com.ta2khu75.quiz.entity.request.RoleRequest;
import com.ta2khu75.quiz.entity.response.RoleResponse;

public interface RoleService extends CrudService<Long, RoleRequest, RoleResponse> {
	List<RoleResponse> readAll();
}
