package com.ta2khu75.quiz.controller;

import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.AccountAuthDetailsResponse;
import com.ta2khu75.quiz.service.AccountService;

@RestController
@RequestMapping("${app.api-prefix}/accounts")
public class AccountController extends BaseController<AccountRequest, AccountResponse, String, AccountService> {
	protected AccountController(AccountService service) {
		super(service);
	}

	@Override
	ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
	}

	@Override
	ResponseEntity<AccountResponse> update(String id, @Valid AccountRequest request) {
//		return ResponseEntity.ok(service.updateInfo(request));
		return null;
	}

	@Override
	ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	ResponseEntity<AccountResponse> read(@PathVariable String id) {
		return ResponseEntity.ok(service.read(id));
	}

	@GetMapping
	public ResponseEntity<PageResponse<AccountAuthDetailsResponse>> search(
			@RequestParam(name = "search", required = false, defaultValue = "") String search,
			@RequestParam(name = "size", required = false, defaultValue = "5") int size,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Pageable pageable = Pageable.ofSize(size).withPage(page - 1);
		return ResponseEntity.ok(service.readPage(search, pageable));
	}
	@PutMapping
	public ResponseEntity<AccountResponse> updateMyAccountInfo(@Valid @RequestBody AccountInfoRequest request) {
		return ResponseEntity.ok(service.updateInfo(request));
	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<AccountAuthDetailsResponse> updateStatusOtherAccount(@PathVariable("id") String id,
//			@Valid @RequestBody AccountStatusRequest request) {
//		return ResponseEntity.ok(service.updateStatus(id, request));
//	}
//

//	@GetMapping("/{id}/details")
//	public ResponseEntity<AccountDetailsResponse> readAccountDetails(@PathVariable("id") String id) {
//		return ResponseEntity.ok(service.readDetails(id));
//	}
//
//	@PatchMapping("/{id}/lock")
//	public ResponseEntity<AccountAuthDetailsResponse> updateLockAccount(@PathVariable("id") String id) {
//		return ResponseEntity.ok(service.updateLock(id));
//
//	}

}
