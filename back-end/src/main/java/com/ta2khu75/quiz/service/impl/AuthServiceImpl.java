package com.ta2khu75.quiz.service.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.entity.Account;
import com.ta2khu75.quiz.entity.request.AuthRequest;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.entity.response.AuthResponse;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.exception.NotMatchesException;
import com.ta2khu75.quiz.mapper.AccountMapper;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.service.AuthService;
import com.ta2khu75.quiz.service.util.JWTUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {
	AccountMapper mapper;
	AuthenticationManagerBuilder authenticationManagerBuilder;
	AccountRepository accountRepository;
	JWTUtil jwtUtil;

	@Override
	public AuthResponse login(AuthRequest authRequest) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				authRequest.getEmail(), authRequest.getPassword());
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		Account account = (Account) authentication.getPrincipal();
		return makeAuthResponse(account);
	}
	@Override
	public AccountResponse getAccount() {
		String email = SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new NotFoundException("Could not found email")); 
		Account account =findAccount(email); 
		return mapper.toResponse(account);
	}

	@Override
	public AuthResponse refreshToken(String token) {
		Jwt jwt = jwtUtil.validateToken(token);
		Account account = validateRefreshToken(jwt.getSubject(), token); 
		return this.makeAuthResponse(account);
	}
	private AuthResponse makeAuthResponse(Account account) {
		AccountResponse accountResponse = mapper.toResponse(account);
		String refreshToken = jwtUtil.createRefreshToken(accountResponse);
		this.updateRefreshToken(account, refreshToken);
		return new AuthResponse(accountResponse, jwtUtil.createToken(accountResponse), jwtUtil.createRefreshToken(accountResponse), true);
	}
	private void updateRefreshToken(Account account, String refreshToken) {
		account.setRefreshToken(refreshToken);
		accountRepository.save(account);
	}
	private Account validateRefreshToken(String email, String refreshToken) {
		Account account = findAccount(email);
		if (!account.getRefreshToken().equals(refreshToken))
			throw new NotMatchesException("Refresh token not invalid");
		return account;
	}
	private Account findAccount(String email) {
		return  accountRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not found account with email: " + email));
	}
	@Override
	public void logout() {
		String email = SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new NotFoundException("Could not found email")); 
		Account account =findAccount(email); 
		account.setRefreshToken(null);
		accountRepository.save(account);
	}
}
