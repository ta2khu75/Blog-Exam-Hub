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

import com.ta2khu75.quiz.anotation.EndpointMapping;
import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.AuthRequest;
import com.ta2khu75.quiz.model.request.update.AccountPasswordRequest;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.AuthResponse;
import com.ta2khu75.quiz.model.response.BooleanResponse;
import com.ta2khu75.quiz.service.AuthService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("${app.api-prefix}/auth")
public class AuthController extends BaseController<AuthService> {
	private long refreshTokenCookieExpiration;
	private long accessTokenCookieExpiration;
	private String refreshTokenCookieName = "refresh_token";
	private String accessTokenCookieName = "access_token";

	public AuthController(AuthService service, @Value("${jwt.refresh.expiration}") long refreshTokenCookieExpiration, @Value("${jwt.expiration}") long accessTokenCookieExpiration) {
		super(service);
		this.refreshTokenCookieExpiration = refreshTokenCookieExpiration;
		this.accessTokenCookieExpiration = accessTokenCookieExpiration;
	}

	@PostMapping("login")
	@EndpointMapping(name = "Login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
		AuthResponse response = service.login(request);

		ResponseCookie cookieRefresh = createCookie(refreshTokenCookieName,response.getRefreshToken(), refreshTokenCookieExpiration);
		ResponseCookie cookieAccess = createCookie(accessTokenCookieName,response.getAccessToken(), accessTokenCookieExpiration);
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookieAccess.toString(), cookieRefresh.toString()).body(response);
	}

	@PostMapping("register")
	@EndpointMapping(name = "Register")
	public ResponseEntity<AccountResponse> register(@Valid @RequestBody AccountRequest request)
			throws MessagingException {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
	}

	@GetMapping("refresh")
	@EndpointMapping(name = "Refresh token")
	public ResponseEntity<AuthResponse> createRefreshToken(@CookieValue("refresh_token") String refreshToken) {
		AuthResponse response = service.refreshToken(refreshToken);
		ResponseCookie cookie = createCookie(refreshTokenCookieName,response.getRefreshToken(), refreshTokenCookieExpiration);
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(response);
	}

	@PutMapping("change-password")
	@EndpointMapping(name = "Change password")
	public ResponseEntity<AccountResponse> changePassword(@Valid @RequestBody AccountPasswordRequest request) {
		return ResponseEntity.ok(service.changePassword(request));
	}

	@GetMapping("logout")
	@EndpointMapping(name = "Logout")
	public ResponseEntity<Void> logout() {
		service.logout();
		ResponseCookie cookie = createCookie(refreshTokenCookieName,null, 0);
		return ResponseEntity.noContent().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
	}

	@GetMapping("check-admin")
	@EndpointMapping(name = "Check admin")
	public ResponseEntity<BooleanResponse> checkAdmin() {
		return ResponseEntity.ok(new BooleanResponse(service.checkAdmin()));
	}

	@GetMapping("verify")
	@EndpointMapping(name = "Verify account")
	public RedirectView verify(@RequestParam String code) {
		boolean isVerified = service.verify(code);
		String clientRedirectUrl;
		if (isVerified) {
			clientRedirectUrl = "http://localhost:5173/login?verified=true";
		} else {
			clientRedirectUrl = "http://localhost:5173/login";
		}
		return new RedirectView(clientRedirectUrl);
	}

	private ResponseCookie createCookie(String name,String value, long expiration) {
		return ResponseCookie.from(name, value).httpOnly(true).secure(true).sameSite("Strict")
				.maxAge(expiration).path("/").build();
	}
}
