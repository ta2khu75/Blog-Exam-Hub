package ta2khu75.com.webquiz.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ta2khu75.com.webquiz.entity.request.AccountRequest;
import ta2khu75.com.webquiz.entity.response.AccountResponse;
import ta2khu75.com.webquiz.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;


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
    public ResponseEntity<Page<AccountResponse>> getMethodName(Pageable pageable) {
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
