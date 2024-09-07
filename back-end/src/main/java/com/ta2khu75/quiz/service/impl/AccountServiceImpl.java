package com.ta2khu75.quiz.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.model.request.update.AccountPasswordRequest;
import com.ta2khu75.quiz.model.request.update.AccountStatusRequest;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.AccountDetailsResponse;
import com.ta2khu75.quiz.exception.ExistingException;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.exception.NotMatchesException;
import com.ta2khu75.quiz.mapper.AccountMapper;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.RoleRepository;
import com.ta2khu75.quiz.scheduling.SendMailScheduling;
import com.ta2khu75.quiz.service.AccountService;
import com.ta2khu75.quiz.util.EmailTemplateUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

import jakarta.mail.MessagingException;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {

	AccountRepository repository;
	AccountMapper mapper;
	RoleRepository roleRepository;
	PasswordEncoder passwordEncoder;
	SendMailScheduling sendMailScheduling;

	@Override
	public AccountResponse create(AccountRequest request) throws MessagingException {
		if (request.getPassword().equals(request.getConfirmPassword())) {
			Account account = mapper.toEntity(request);
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
	public AccountDetailsResponse update(String id, AccountStatusRequest request) {
		Account account = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found account with id: " + id));
		mapper.update(request, account);
		if (!account.getRole().getId().equals(request.getRoleId())) {
			account.setRole(roleRepository.findById(request.getRoleId())
					.orElseThrow(() -> new NotFoundException("Could not found role with id: " + request.getRoleId())));
		}
		return mapper.toDetailsResponse(repository.save(account));
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

	@Override
	public AccountResponse read(String id) {
		return mapper.toResponse(repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found account with id: " + id)));
	}

	@Override
	public PageResponse<AccountDetailsResponse> readPage(String search, Pageable pageable) {
		PageResponse<AccountDetailsResponse> response = mapper
				.toPageResponse(repository.searchByDisplayNameOrEmail(search, pageable));
		response.setNumber(response.getNumber() + 1);
		return response;
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
	public AccountResponse update(AccountInfoRequest request) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		Account account = repository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not find account with email: " + email));
		mapper.update(request, account);
		return mapper.toResponse(repository.save(account));
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
	public AccountResponse readMyAccount() {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		Account account = repository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not find account with email: " + email));
		return mapper.toResponse(account);
	}

//	@Override
//	public AccountDetailsResponse update(String id, AccountPermissionRequest request) {
//		Account account = repository.findById(id)
//				.orElseThrow(() -> new NotFoundException("Could not found account with id: " + id));
//		List<Permission> list= permissionRepository.findAllById(request.getPermissionIds());
//		Role role = account.getRole();
//		role.setPermissions(new HashSet<>(permissionRepository.findAllById(request.getPermissionIds())));
////		permissionRepository.findAllById(request.getPermissionIds()).stream().map(permission -> {
////			
////			return permission;
////		}).toList();
//		account.setRole(roleRepository.save(role));
////		account.setRole(account.getRole()permissionRepository.findAllById(request.getPermissionIds()));
//		return mapper.toDetailsResponse(repository.save(account));
//	}
}
