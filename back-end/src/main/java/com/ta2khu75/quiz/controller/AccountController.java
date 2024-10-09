package com.ta2khu75.quiz.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.model.request.update.AccountPasswordRequest;
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

	@PostMapping()
	public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request)
			throws MessagingException {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
	}

	@PutMapping
	public ResponseEntity<AccountResponse> updateMyInfoAccount(@Valid @RequestBody AccountInfoRequest request) {
		return ResponseEntity.ok(service.updateInfo(request));
	}

	@GetMapping
	public ResponseEntity<PageResponse<AccountAuthDetailsResponse>> readPage(
			@RequestParam(name = "search", required = false, defaultValue = "") String search,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		return ResponseEntity.ok(service.readPage(search, pageable));
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

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOtherAccount(@PathVariable("id") String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/change-password")
	public ResponseEntity<AccountResponse> updateMyPasswordAccount(@RequestBody AccountPasswordRequest request) {
		return ResponseEntity.ok(service.updatePassword(request));
	}

	@GetMapping("/verify")
	public RedirectView verifyAccount(@RequestParam(name = "code") String code) {
		boolean isVerified = service.verify(code);
		String clientRedirectUrl;
		if (isVerified) {
			clientRedirectUrl = "http://localhost:5173/login?verified=true";
		} else {
			clientRedirectUrl = "http://localhost:5173/login";
		}
		return new RedirectView(clientRedirectUrl);
	}
	@GetMapping("/{id}/details")
	@Transactional
	public ResponseEntity<AccountDetailsResponse> readAccountDetails(@PathVariable("id") String id) {
		return ResponseEntity.ok(service.readDetails(id));
	}
}
