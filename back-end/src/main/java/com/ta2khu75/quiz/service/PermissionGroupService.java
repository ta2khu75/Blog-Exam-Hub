package com.ta2khu75.quiz.service;

import java.util.List;

import com.ta2khu75.quiz.entity.response.PermissionGroupResponse;

public interface PermissionGroupService {

	List<PermissionGroupResponse> readAll();
}
