package com.ta2khu75.quiz.service.impl;

import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.entity.request.PermissionRequest;
import com.ta2khu75.quiz.entity.response.PermissionResponse;
import com.ta2khu75.quiz.repository.PermissionRepository;
import com.ta2khu75.quiz.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissonServiceImpl implements PermissionService {
	PermissionRepository repository;
	@Override
	public PermissionResponse create(PermissionRequest request) {
		return null;
	}

	@Override
	public PermissionResponse update(Long id, PermissionRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PermissionResponse read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
