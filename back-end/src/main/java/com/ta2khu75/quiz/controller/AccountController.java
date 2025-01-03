package com.ta2khu75.quiz.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.model.request.update.AccountStatusRequest;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.AccountAuthDetailsResponse;
import com.ta2khu75.quiz.model.response.details.AccountDetailsResponse;
import com.ta2khu75.quiz.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/account")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
	AccountService service;

	@PostMapping
	public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request)
			throws MessagingException {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
	}

	@PutMapping
	public ResponseEntity<AccountResponse> updateMyInfoAccount(@Valid @RequestBody AccountInfoRequest request) {
		return ResponseEntity.ok(service.updateInfo(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountResponse> readInfoOtherAccount(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.read(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AccountAuthDetailsResponse> updateStatusOtherAccount(@PathVariable("id") String id,
			@Valid @RequestBody AccountStatusRequest request) {
		return ResponseEntity.ok(service.updateStatus(id, request));
	}

	@GetMapping
	public ResponseEntity<PageResponse<AccountAuthDetailsResponse>> readPage(
			@RequestParam(name = "search", required = false, defaultValue = "") String search,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		return ResponseEntity.ok(service.readPage(search, pageable));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOtherAccount(@PathVariable("id") String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}


	@GetMapping("/{id}/details")
	public ResponseEntity<AccountDetailsResponse> readAccountDetails(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.readDetails(id));
	}

	@PatchMapping("/{id}/lock")
	public ResponseEntity<AccountAuthDetailsResponse> updateLockAccount(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.updateLock(id));

	}

}
