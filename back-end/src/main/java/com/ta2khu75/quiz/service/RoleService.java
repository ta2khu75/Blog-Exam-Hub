package com.ta2khu75.quiz.service;

import java.util.List;

import com.ta2khu75.quiz.model.entity.Role;
import com.ta2khu75.quiz.model.request.RoleRequest;
import com.ta2khu75.quiz.model.response.details.RoleDetailResponse;
import com.ta2khu75.quiz.service.base.CrudService;

public interface RoleService extends CrudService<RoleRequest, RoleDetailResponse, Long> {
	List<RoleDetailResponse> readAll();
	Role find(Long id);
	Role readByName(String roleName);
}
