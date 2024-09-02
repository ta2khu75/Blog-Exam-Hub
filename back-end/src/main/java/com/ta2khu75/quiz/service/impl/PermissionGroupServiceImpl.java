package com.ta2khu75.quiz.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.entity.response.PermissionGroupResponse;
import com.ta2khu75.quiz.mapper.PermissionGroupMapper;
import com.ta2khu75.quiz.repository.PermissionGroupRepository;
import com.ta2khu75.quiz.service.PermissionGroupService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionGroupServiceImpl implements PermissionGroupService {
	PermissionGroupRepository repository;
	PermissionGroupMapper mapper;

	@Override
	public List<PermissionGroupResponse> readAll() {
		return repository.findAll().stream().map(mapper::toResponse).toList();
	}

}
