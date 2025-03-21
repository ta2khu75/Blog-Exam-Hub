package com.ta2khu75.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.model.request.RoleRequest;
import com.ta2khu75.quiz.model.response.RoleResponse;
import com.ta2khu75.quiz.service.RoleService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${app.api-prefix}/roles")
public class RoleController extends BaseController<RoleService>
		implements CrudController<RoleRequest, RoleResponse, Long> {
	public RoleController(RoleService service) {
		super(service);
	}

	@GetMapping
	@EndpointMapping(name="Read all role")
	public ResponseEntity<List<RoleResponse>> readAll() {
		return ResponseEntity.ok(service.readAll());
	}

	@Override
	@EndpointMapping(name="Create role")
	public ResponseEntity<RoleResponse> create(@Valid @RequestBody RoleRequest request) {
		return ResponseEntity.ok(service.create(request));
	}

	@Override
	@EndpointMapping(name="Update role")
	public ResponseEntity<RoleResponse> update(@PathVariable Long id, @Valid @RequestBody RoleRequest request) {
		return ResponseEntity.ok(service.update(id, request));
	}

	@Override
	@EndpointMapping(name="Delete role")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<RoleResponse> read(@PathVariable Long id) {
		return ResponseEntity.ok(service.read(id));
	}
}
