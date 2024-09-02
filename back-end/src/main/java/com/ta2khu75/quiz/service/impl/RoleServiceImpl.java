package com.ta2khu75.quiz.service.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.entity.Permission;
import com.ta2khu75.quiz.entity.Role;
import com.ta2khu75.quiz.entity.request.RoleRequest;
import com.ta2khu75.quiz.entity.response.RoleResponse;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.RoleMapper;
import com.ta2khu75.quiz.repository.PermissionRepository;
import com.ta2khu75.quiz.repository.RoleRepository;
import com.ta2khu75.quiz.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
	RoleRepository repository;
	PermissionRepository permissionRepository;
	RoleMapper mapper;

	@Override
	public RoleResponse create(RoleRequest request) {
		Role role = mapper.toEntity(request);
		role.setPermissions(new HashSet<>(permissionRepository.findAllById(request.getPermissionIds())));
		return mapper.toResponse(repository.save(role));
	}

	@Override
	public RoleResponse update(Long id, RoleRequest request) {
		Role role = this.find(id);
		mapper.update(request, role);
		if (role.getPermissions().stream().map(Permission::getId).noneMatch(request.getPermissionIds()::contains)) {
			role.setPermissions(new HashSet<>(permissionRepository.findAllById(request.getPermissionIds())));
		}
		return mapper.toResponse(repository.save(role));
	}

	private Role find(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Could not found role with id: " + id));
	}
	@Override
	public RoleResponse read(Long id) {
		return mapper.toResponse(this.find(id));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<RoleResponse> readAll() {
		return repository.findAll().stream().map(mapper::toResponse).toList();
	}

}
