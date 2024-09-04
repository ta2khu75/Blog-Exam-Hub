package com.ta2khu75.quiz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.model.response.PermissionResponse;
import com.ta2khu75.quiz.mapper.PermissionMapper;
import com.ta2khu75.quiz.repository.PermissionRepository;
import com.ta2khu75.quiz.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissonServiceImpl implements PermissionService {
	PermissionRepository repository;
	PermissionMapper mapper;
	@Override
	public List<PermissionResponse> readAllPermission() {
		return repository.findAll().stream().map(mapper::toResponse).toList();
	}

}
