package com.ta2khu75.quiz.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.AuthRequest;
import com.ta2khu75.quiz.model.request.update.AccountPasswordRequest;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.AuthResponse;
import com.ta2khu75.quiz.service.AuthService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/auth")
public class AuthController {
	@Value("${jwt.refresh.expiration}")
	private long cookieExpiration;

	private final AuthService service;

	@PostMapping("login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
		AuthResponse response = service.login(request);
		ResponseCookie cookie = createRefreshTokenCookie(response.getRefreshToken(), cookieExpiration);
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(response);
	}

	@PostMapping("register")
	public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request)
			throws MessagingException {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
	}

	@GetMapping("refresh-token")
	public ResponseEntity<AuthResponse> createRefreshToken(@CookieValue("refresh_token") String refreshToken) {
		AuthResponse response = service.refreshToken(refreshToken);
		ResponseCookie cookie = createRefreshTokenCookie(response.getRefreshToken(), cookieExpiration);
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(response);
	}
	@PutMapping("/change-password")
	public ResponseEntity<AccountResponse> changePassword(@Valid @RequestBody AccountPasswordRequest request) {
		return ResponseEntity.ok(service.changePassword(request));
	}

	@GetMapping("logout")
	public ResponseEntity<Void> logout() {
		service.logout();
		ResponseCookie cookie = createRefreshTokenCookie(null, 0);
		return ResponseEntity.noContent().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
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
	private ResponseCookie createRefreshTokenCookie(String refreshToken, long cookieExpiration) {
		return ResponseCookie.from("refresh_token", refreshToken).httpOnly(true).secure(true).sameSite("None")
				.maxAge(cookieExpiration).path("/").build();
	}
}
