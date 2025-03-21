package com.ta2khu75.quiz.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.model.request.RoleRequest;
import com.ta2khu75.quiz.model.response.RoleResponse;
import com.ta2khu75.quiz.event.RoleChangeEvent;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.mapper.RoleMapper;
import com.ta2khu75.quiz.model.entity.Permission;
import com.ta2khu75.quiz.model.entity.Role;
import com.ta2khu75.quiz.repository.PermissionRepository;
import com.ta2khu75.quiz.repository.RoleRepository;
import com.ta2khu75.quiz.service.RoleService;
import com.ta2khu75.quiz.service.base.BaseService;
import com.ta2khu75.quiz.service.util.RedisUtil;
import com.ta2khu75.quiz.service.util.RedisUtil.NameModel;

import jakarta.transaction.Transactional;

@Service
public class RoleServiceImpl extends BaseService<RoleRepository, RoleMapper> implements RoleService {

	private final PermissionRepository permissionRepository;
	private final ApplicationEventPublisher eventPublisher;
	private final RedisUtil redisUtil;

	public RoleServiceImpl(RoleRepository repository, RoleMapper mapper, PermissionRepository permissionRepository,
			ApplicationEventPublisher eventPublisher, RedisUtil redisUtil) {
		super(repository, mapper);
		this.permissionRepository = permissionRepository;
		this.eventPublisher = eventPublisher;
		this.redisUtil = redisUtil;
	}

	@Override
	public RoleResponse create(RoleRequest request) {
		Role role = mapper.toEntity(request);
		role.setName(role.getName().toUpperCase());
		role.setPermissions(new HashSet<>(permissionRepository.findAllById(request.getPermissionIds())));
		return mapper.toDetailResponse(repository.save(role));
	}

	@Override
	public RoleResponse update(Long id, RoleRequest request) {
		Role role = this.find(id);
		mapper.update(request, role);
		role.setName(role.getName().toUpperCase());
		if (request.getPermissionIds().size() != role.getPermissions().size() || !role.getPermissions().stream()
				.map(Permission::getId).collect(Collectors.toSet()).equals(request.getPermissionIds())) {
			role.setPermissions(new HashSet<>(permissionRepository.findAllById(request.getPermissionIds())));
		}
		role = repository.save(role);
		eventPublisher.publishEvent(new RoleChangeEvent(this, role));
		return mapper.toDetailResponse(role);
	}

	@Override
	public RoleResponse read(Long id) {
		return mapper.toDetailResponse(this.find(id));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<RoleResponse> readAll() {
		return repository.findAll().stream().map(mapper::toDetailResponse).toList();
	}

	@Override
	public Role find(Long id) {
		Role role = redisUtil.read(NameModel.ROLE, id, Role.class);
		if (role == null) {
			role = repository.findById(id)
					.orElseThrow(() -> new NotFoundException("Could not found role with id: " + id));
			role.getPermissions().size();
			redisUtil.create(NameModel.ROLE, role.getName(), role);
		}
		return role;
	}

	@Override
	@Transactional
	public Role readByName(String roleName) {
		Role role = null;
		try {
			role = redisUtil.read(NameModel.ROLE, roleName, Role.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (role == null) {
			role = repository.findByName(roleName)
					.orElseThrow(() -> new NotFoundException("Could not found role with name: " + roleName));
			role.getPermissions().size();
			try {
				redisUtil.create(NameModel.ROLE, roleName, role);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(role.toString());
		return role;
	}

}
