package com.ta2khu75.quiz.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import com.ta2khu75.quiz.service.util.RedisUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl  implements RoleService {
	RoleRepository repository;
	PermissionRepository permissionRepository;
	ApplicationEventPublisher eventPublisher;
	RoleMapper mapper;
	RedisUtil redisUtil;

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
		if (request.getPermissionIds().size() != role.getPermissions().size() || !role.getPermissions().stream().map(Permission::getId).collect(Collectors.toSet()).equals(request.getPermissionIds())) {
			role.setPermissions(new HashSet<>(permissionRepository.findAllById(request.getPermissionIds())));
		}
		role = repository.save(role);
		eventPublisher.publishEvent(new RoleChangeEvent(this, role.getId()));
		return mapper.toDetailsResponse(role);
	}

	@Override
	public Role find(Long id) {
		Role role = redisUtil.read(id.toString(), Role.class);
		if (role == null) {
			role = repository.findById(id)
					.orElseThrow(() -> new NotFoundException("Could not found role with id: " + id));
			role.getPermissions().size();
			redisUtil.create(id.toString(), role);
		}
		return role;
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
