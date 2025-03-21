package com.ta2khu75.quiz.service;

import java.util.List;

import com.ta2khu75.quiz.model.entity.Role;
import com.ta2khu75.quiz.model.request.RoleRequest;
import com.ta2khu75.quiz.model.response.RoleResponse;
import com.ta2khu75.quiz.service.base.CrudService;

public interface RoleService extends CrudService<RoleRequest, RoleResponse, Long> {
	List<RoleResponse> readAll();
	Role find(Long id);
	Role readByName(String roleName);
}
