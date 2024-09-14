package com.ta2khu75.quiz.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.model.request.RoleRequest;
import com.ta2khu75.quiz.model.response.details.RoleDetailsResponse;
import com.ta2khu75.quiz.event.RoleChangeEvent;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.RoleMapper;
import com.ta2khu75.quiz.model.entity.Permission;
import com.ta2khu75.quiz.model.entity.Role;
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
	ApplicationEventPublisher eventPublisher;
	RoleMapper mapper;

	@Override
	public RoleDetailsResponse create(RoleRequest request) {
		Role role = mapper.toEntity(request);
		role.setPermissions(new HashSet<>(permissionRepository.findAllById(request.getPermissionIds())));
		return mapper.toDetailsResponse(repository.save(role));
	}

	@Override
	public RoleDetailsResponse update(Long id, RoleRequest request) {
		Role role = this.find(id);
		mapper.update(request, role);
		if (role.getPermissions().stream().map(Permission::getId).noneMatch(request.getPermissionIds()::contains)) {
			role.setPermissions(new HashSet<>(permissionRepository.findAllById(request.getPermissionIds())));
		}
		role = repository.save(role);
		if (role.getName().equals("ANONYMOUS")) {
			eventPublisher.publishEvent(new RoleChangeEvent(this));
		}
		return mapper.toDetailsResponse(role);
	}

	private Role find(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Could not found role with id: " + id));
	}

	@Override
	public RoleDetailsResponse read(Long id) {
		return mapper.toDetailsResponse(this.find(id));

	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<RoleDetailsResponse> readAll() {
		return repository.findAll().stream().map(mapper::toDetailsResponse).toList();
	}

}
