package com.ta2khu75.quiz.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ta2khu75.quiz.entity.request.RoleRequest;
import com.ta2khu75.quiz.entity.response.RoleResponse;
import com.ta2khu75.quiz.service.RoleService;

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
	public ResponseEntity<List<RoleResponse>> readAllRole() {
		return ResponseEntity.ok(roleService.readAll());
	}
	@PostMapping
	public ResponseEntity<RoleResponse> createRole(RoleRequest request) {
		return ResponseEntity.ok(roleService.create(request));
	}
	@PutMapping("{id}")
	public ResponseEntity<RoleResponse> updateRole(@PathVariable("id") Long id, RoleRequest request) {
		return ResponseEntity.ok(roleService.update(id, request));
	}
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id) {
		roleService.delete(id);
		return ResponseEntity.noContent().build();
	}
	@GetMapping("{id}")
	public ResponseEntity<RoleResponse> readRole(@PathVariable("id") Long id) {
		return ResponseEntity.ok(roleService.read(id));
	}
}
