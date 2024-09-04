package com.ta2khu75.quiz.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta2khu75.quiz.model.request.AuthRequest;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.AuthResponse;
import com.ta2khu75.quiz.service.AuthService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api-prefix}/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
	@Value("${jwt.refresh.expiration}")
	@NonFinal
	long cookieExpiration;

	AuthService service;

	@PostMapping("login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
		AuthResponse response = service.login(request);
		ResponseCookie cookie = createRefreshTokenCookie(response.getRefreshToken(), cookieExpiration);
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(response);
	}

	@GetMapping("account")
	public ResponseEntity<AccountResponse> readMyAccount() {
		return ResponseEntity.ok(service.getAccount());
	}

	@GetMapping("refresh-token")
	public ResponseEntity<AuthResponse> createRefreshToken(@CookieValue("refresh_token") String refreshToken) {
		AuthResponse response = service.refreshToken(refreshToken);
		ResponseCookie cookie = createRefreshTokenCookie(response.getRefreshToken(), cookieExpiration);
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(response);
	}

	@GetMapping("logout")
	public ResponseEntity<Void> logout() {
		ResponseCookie cookie = createRefreshTokenCookie(null, 0);
		return ResponseEntity.noContent().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
	}

	private ResponseCookie createRefreshTokenCookie(String refreshToken, long cookieExpiration) {
		return ResponseCookie.from("refresh_token", refreshToken).httpOnly(true).secure(true).sameSite("None")
				.maxAge(cookieExpiration).path("/").build();
	}
}
