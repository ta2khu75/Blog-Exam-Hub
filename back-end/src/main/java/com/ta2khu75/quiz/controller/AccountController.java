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
import org.springframework.web.servlet.view.RedirectView;

import com.ta2khu75.quiz.entity.request.AccountRequest;
import com.ta2khu75.quiz.entity.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.entity.request.update.AccountPasswordRequest;
import com.ta2khu75.quiz.entity.request.update.AccountStatusRequest;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.entity.response.PageResponse;
import com.ta2khu75.quiz.entity.response.details.AccountDetailsResponse;
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
	public ResponseEntity<AccountResponse> updateMyAccount(@Valid @RequestBody AccountInfoRequest request) {
		return ResponseEntity.ok(service.update(request));
	}

	@GetMapping
	public ResponseEntity<PageResponse<AccountDetailsResponse>> readPage(
			@RequestParam(name = "search", required = false, defaultValue = "") String search,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		return ResponseEntity.ok(service.readPage(search, pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountResponse> readInfoOtherAccount(@PathVariable String id) {
		return ResponseEntity.ok(service.read(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AccountDetailsResponse> updateStatusOtherAccount(@PathVariable("id") String id,
			@Valid @RequestBody AccountStatusRequest request) {
		return ResponseEntity.ok(service.update(id, request));
	}
//	@PutMapping("/{id}/permission")
//	public ResponseEntity<AccountDetailsResponse> updatePermissionOtherAccount(@PathVariable("id") String id,
//			@Valid @RequestBody AccountPermissionRequest request) {
//		return ResponseEntity.ok(service.update(id, request));
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOtherAccount(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/change-password")
	public ResponseEntity<AccountResponse> updatePasswordAccount(@RequestBody AccountPasswordRequest request) {
		return ResponseEntity.ok(service.changePassword(request));
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
}
