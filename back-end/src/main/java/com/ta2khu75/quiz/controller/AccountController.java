package com.ta2khu75.quiz.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.model.request.update.AccountStatusRequest;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.ManagedAccountResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.AccountDetailResponse;
import com.ta2khu75.quiz.service.AccountService;

@RestController
@RequestMapping("${app.api-prefix}/accounts")
public class AccountController extends BaseController<AccountService> {
	public AccountController(AccountService service) {
		super(service);
	}

	@PostMapping
	@EndpointMapping(name = "Create account")
	public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
	}

	@DeleteMapping
	@EndpointMapping(name = "Delete account")
	ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	@EndpointMapping(name = "Read account")
	ResponseEntity<AccountDetailResponse> read(@PathVariable String id) {
		return ResponseEntity.ok(service.read(id));
	}
//	@GetMapping("{id}/detail")
//	@EndpointMapping(name = "Read account")
//	ResponseEntity<AccountDetailResponse> readDetails(@PathVariable String id) {
//		return ResponseEntity.ok(service.read(id));
//	}

	@GetMapping
	@EndpointMapping(name = "Search account status")
	public ResponseEntity<PageResponse<ManagedAccountResponse>> searchStatus(
			@RequestParam(name = "search", required = false, defaultValue = "") String search,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		return ResponseEntity.ok(service.readPage(search, pageable));
	}

	@PutMapping
	@EndpointMapping(name = "Update account info")
	public ResponseEntity<AccountResponse> updateInfo(@Valid @RequestBody AccountInfoRequest request) {
		return ResponseEntity.ok(service.updateInfo(request));
	}

	@PutMapping("/{id}")
	@EndpointMapping(name = "Update account status")
	public ResponseEntity<ManagedAccountResponse> updateStatus(@PathVariable String id,
			@Valid @RequestBody AccountStatusRequest request) {
		return ResponseEntity.ok(service.updateStatus(id, request));
	}

//
//	@PatchMapping("/{id}/lock")
//	public ResponseEntity<AccountAuthDetailsResponse> updateLockAccount(@PathVariable("id") String id) {
//		return ResponseEntity.ok(service.updateLock(id));
//
//	}

}
