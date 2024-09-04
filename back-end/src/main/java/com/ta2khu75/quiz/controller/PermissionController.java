package com.ta2khu75.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ta2khu75.quiz.model.response.PermissionResponse;
import com.ta2khu75.quiz.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/permission")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
	PermissionService permissionService;
	@GetMapping
	public ResponseEntity<List<PermissionResponse>> readAllPermission() {
		return ResponseEntity.ok(permissionService.readAllPermission());
	}
}
