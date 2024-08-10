package com.ta2khu75.quiz.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import com.ta2khu75.quiz.entity.request.AccountRequest;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.entity.response.PageResponse;
import com.ta2khu75.quiz.service.AccountService;


@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/account")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService service;
    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest request) {
        return ResponseEntity.ok(service.create(request));
    }
    @GetMapping
    public ResponseEntity<PageResponse<AccountResponse>> getMethodName(Pageable pageable) {

        return ResponseEntity.ok(service.readPage(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> read(@PathVariable Long id) {
        return ResponseEntity.ok(service.read(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> update(@PathVariable Long id, @Valid @RequestBody AccountRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
