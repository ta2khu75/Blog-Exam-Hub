package com.ta2khu75.quiz.service;

import java.util.List;

import com.ta2khu75.quiz.entity.response.PermissionResponse;

public interface PermissionService {
	List<PermissionResponse> readAllPermission();
}