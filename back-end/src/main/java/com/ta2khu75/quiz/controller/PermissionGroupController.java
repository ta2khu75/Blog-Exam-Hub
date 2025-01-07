package com.ta2khu75.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.model.response.PermissionGroupResponse;
import com.ta2khu75.quiz.service.PermissionGroupService;

@Controller
@RequestMapping("${app.api-prefix}/permission-groups")
public class PermissionGroupController extends BaseController<PermissionGroupService> {
	public PermissionGroupController(PermissionGroupService service) {
		super(service);
	}

	@GetMapping
	@EndpointMapping(name="Read all permission group")
	public ResponseEntity<List<PermissionGroupResponse>> readAll() {
		return ResponseEntity.ok(service.readAll());
	}
}
