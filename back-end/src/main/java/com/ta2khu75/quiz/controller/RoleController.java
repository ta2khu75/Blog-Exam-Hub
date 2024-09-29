package com.ta2khu75.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ta2khu75.quiz.model.request.RoleRequest;
import com.ta2khu75.quiz.model.response.RoleResponse;
import com.ta2khu75.quiz.model.response.details.RoleDetailsResponse;
import com.ta2khu75.quiz.service.RoleService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("${app.api-prefix}/role")
public class RoleController {
	RoleService roleService;
	@GetMapping
	public ResponseEntity<List<RoleDetailsResponse>> readAllRole() {
		return ResponseEntity.ok(roleService.readAll());
	}
	@PutMapping("{id}")
	public ResponseEntity<RoleResponse> updateRole(@PathVariable("id") Long id, @Valid @RequestBody RoleRequest request) {
		return ResponseEntity.ok(roleService.update(id, request));
	}
}
