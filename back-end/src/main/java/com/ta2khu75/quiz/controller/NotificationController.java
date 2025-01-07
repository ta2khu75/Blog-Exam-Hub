package com.ta2khu75.quiz.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.model.response.NotificationResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.service.NotificationService;

@RestController
@RequestMapping("${app.api-prefix}/notifications")
public class NotificationController extends BaseController<NotificationService> {

	public NotificationController(NotificationService service) {
		super(service);
	}

	@GetMapping("/account/{accountId}")
	@EndpointMapping(name="Read page notification")
	public ResponseEntity<PageResponse<NotificationResponse>> readPage(
			@PathVariable String accountId,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		return ResponseEntity.ok(service.readPageByAccountId(accountId, pageable));
	}
}
