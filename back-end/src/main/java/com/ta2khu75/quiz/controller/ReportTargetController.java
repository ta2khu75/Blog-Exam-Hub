package com.ta2khu75.quiz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ta2khu75.quiz.exception.AdviceException.ApiResponse;
import com.ta2khu75.quiz.model.request.ReportTargetRequest;
import com.ta2khu75.quiz.model.response.ReportTargetResponse;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.service.ReportTargetService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${app.api-prefix}/report-target")
public class ReportTargetController {
//	ReportTargetService reportTargetService;

	@PostMapping
	public ResponseEntity<ReportTargetResponse> create(@ModelAttribute ReportTargetRequest request) {
//		return ResponseEntity.ok(reportTargetService.create(request));
		return null;
	}
}
