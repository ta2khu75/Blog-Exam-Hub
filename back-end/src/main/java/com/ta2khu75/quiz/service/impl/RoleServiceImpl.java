package com.ta2khu75.quiz.service.impl;

import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.entity.request.RoleRequest;
import com.ta2khu75.quiz.entity.response.RoleResponse;
import com.ta2khu75.quiz.repository.RoleRepository;
import com.ta2khu75.quiz.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleServiceImpl implements RoleService{
	RoleRepository repository;
	@Override
	public RoleResponse create(RoleRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleResponse update(Long id, RoleRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleResponse read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
