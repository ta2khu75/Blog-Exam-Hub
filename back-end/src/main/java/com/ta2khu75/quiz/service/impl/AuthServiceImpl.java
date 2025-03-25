package com.ta2khu75.quiz.service.impl;

import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
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
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.AuthResponse;
import com.ta2khu75.quiz.exception.ExistingException;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.exception.NotMatchesException;
import com.ta2khu75.quiz.mapper.AccountMapper;
import com.ta2khu75.quiz.model.RoleDefault;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.RoleRepository;
import com.ta2khu75.quiz.scheduling.SendMailScheduling;
import com.ta2khu75.quiz.service.AuthService;
import com.ta2khu75.quiz.service.util.JWTUtil;
import com.ta2khu75.quiz.util.EmailTemplateUtil;
import com.ta2khu75.quiz.util.FunctionUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

import jakarta.mail.MessagingException;

@Service
public class AuthServiceImpl implements AuthService {
	private final JWTUtil jwtUtil;
	private final AccountMapper mapper;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private final AccountRepository repository;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final SendMailScheduling sendMailScheduling;

	public AuthServiceImpl(JWTUtil jwtUtil, AccountMapper mapper, PasswordEncoder passwordEncoder,
			RoleRepository roleRepository, AccountRepository repository,
			AuthenticationManagerBuilder authenticationManagerBuilder, SendMailScheduling sendMailScheduling) {
		super();
		this.jwtUtil = jwtUtil;
		this.mapper = mapper;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
		this.repository = repository;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.sendMailScheduling = sendMailScheduling;
	}

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
		AccountResponse accountResponse = mapper.toResponse(account);
		String refreshToken = jwtUtil.createRefreshToken(account);
		this.updateRefreshToken(account, refreshToken);
		return new AuthResponse(accountResponse, jwtUtil.createToken(account), jwtUtil.createRefreshToken(account),
				true);
	}

	private void updateRefreshToken(Account account, String refreshToken) {
		account.setRefreshToken(refreshToken);
		repository.save(account);
	}

	private Account validateRefreshToken(String email, String refreshToken) {
		Account account = findById();
		if (!account.getRefreshToken().equals(refreshToken))
			throw new NotMatchesException("Refresh token not invalid");
		return account;
	}

	private Account findById() {
		return FunctionUtil.findOrThrow(SecurityUtil.getCurrentUserLogin(), Account.class, repository::findById);
	}

	@Override
	public void logout() {
		Account account = findById();
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
			account.setRole(roleRepository.findByName(RoleDefault.USER.name())
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
		Account account = findById();
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

	@Override
	public boolean checkAdmin() {
		String accountId = SecurityUtil.getCurrentUserLogin();
		return repository.existsByIdAndRoleName(accountId, RoleDefault.ADMIN.name());
	}
}
