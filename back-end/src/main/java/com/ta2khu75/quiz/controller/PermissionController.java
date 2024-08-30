package com.ta2khu75.quiz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ta2khu75.quiz.entity.request.PermissionRequest;
import com.ta2khu75.quiz.entity.response.PermissionResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/permission")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
	@PostMapping
	public ResponseEntity<PermissionResponse> create(PermissionRequest request) {
		return ResponseEntity.ok(new PermissionResponse());
	}
}
