package com.ta2khu75.quiz.service.impl;

import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.AuthRequest;
import com.ta2khu75.quiz.model.request.update.AccountPasswordRequest;
import com.ta2khu75.quiz.model.response.AccountAuthResponse;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.AuthResponse;
import com.ta2khu75.quiz.model.response.details.AccountDetailsResponse;
import com.ta2khu75.quiz.exception.ExistingException;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.exception.NotMatchesException;
import com.ta2khu75.quiz.mapper.AccountMapper;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.RoleRepository;
import com.ta2khu75.quiz.scheduling.SendMailScheduling;
import com.ta2khu75.quiz.service.AuthService;
import com.ta2khu75.quiz.service.util.JWTUtil;
import com.ta2khu75.quiz.util.EmailTemplateUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {
	JWTUtil jwtUtil;
	AccountMapper mapper;
	PasswordEncoder passwordEncoder;
	RoleRepository roleRepository;
	AccountRepository repository;
	AuthenticationManagerBuilder authenticationManagerBuilder;
	SendMailScheduling sendMailScheduling;

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
	public AuthResponse refreshToken(String token) {
		Jwt jwt = jwtUtil.validateToken(token);
		Account account = validateRefreshToken(jwt.getSubject(), token);
		return this.makeAuthResponse(account);
	}

	private AuthResponse makeAuthResponse(Account account) {
		AccountAuthResponse accountAuthResponse = mapper.toAuthResponse(account);
		String refreshToken = jwtUtil.createRefreshToken(accountAuthResponse);
		this.updateRefreshToken(account, refreshToken);
		return new AuthResponse(accountAuthResponse, jwtUtil.createToken(accountAuthResponse),
				jwtUtil.createRefreshToken(accountAuthResponse), true);
	}

	private void updateRefreshToken(Account account, String refreshToken) {
		account.setRefreshToken(refreshToken);
		repository.save(account);
	}

	private Account validateRefreshToken(String email, String refreshToken) {
		Account account = findAccount(email);
		if (!account.getRefreshToken().equals(refreshToken))
			throw new NotMatchesException("Refresh token not invalid");
		return account;
	}

	private Account findAccount(String email) {
		return repository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not found account with email: " + email));
	}

	@Override
	public void logout() {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not found email"));
		Account account = findAccount(email);
		account.setRefreshToken(null);
		repository.save(account);
	}

	@Override
	public AccountResponse register(AccountRequest accountRequest) throws MessagingException {
		if (accountRequest.getPassword().equals(accountRequest.getConfirmPassword())) {
			 if (repository.existsByEmail(accountRequest.getEmail())) {
			        throw new ExistingException("Email already exists");
			    }
			Account account = mapper.toEntity(accountRequest);
			account.setEmail(account.getEmail().toLowerCase());
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			account.setRole(roleRepository.findByName("USER")
					.orElseThrow(() -> new NotFoundException("Could not find role with name: USER")));
			account.setCodeVerify(UUID.randomUUID().toString());
			account.setDisplayName(account.getFirstName() + " " + account.getLastName());
			try {
				account = repository.save(account);
			} catch (DataIntegrityViolationException e) {
				throw new ExistingException("Email already exists");
			}
			sendMailScheduling.addMail(account.getEmail(), "Confirm your email",
					EmailTemplateUtil.getVerify(account.getCodeVerify()), true);
			return mapper.toResponse(account);
		}
		throw new NotMatchesException("password and confirm password not matches");
	}

	@Override
	public AccountResponse changePassword(AccountPasswordRequest request) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		Account account = repository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not find account with email: " + email));
		if (passwordEncoder.matches(request.getPassword(), account.getPassword())) {
			if (request.getNewPassword().equals(request.getConfirmPassword())) {
				account.setPassword(passwordEncoder.encode(request.getNewPassword()));
				return mapper.toResponse(repository.save(account));
			}
			throw new NotMatchesException("New password and confirm password not matches");
		}
		throw new NotMatchesException("Password not matches");
	}
	
	@Override
	public boolean verify(String code) {
		Account account = repository.findByCodeVerify(code).orElse(null);
		if (account != null) {
			account.setCodeVerify(null);
			account.setEnabled(true);
			repository.save(account);
			return true;
		}
		return false;
	}
}
