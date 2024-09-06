package com.ta2khu75.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ta2khu75.quiz.model.response.PermissionGroupResponse;
import com.ta2khu75.quiz.service.PermissionGroupService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/permission-group")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionGroupController {
	PermissionGroupService service;
	@GetMapping
	public ResponseEntity<List<PermissionGroupResponse>> readAllPermissionGroup() {
		return ResponseEntity.ok(service.readAll());
	}
}
