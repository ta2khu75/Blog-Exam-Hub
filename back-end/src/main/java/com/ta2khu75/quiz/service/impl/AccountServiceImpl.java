package com.ta2khu75.quiz.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.model.request.update.AccountStatusRequest;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.ManagedAccountResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.AccountDetailResponse;
import com.ta2khu75.quiz.exception.ExistingException;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.exception.NotMatchesException;
import com.ta2khu75.quiz.mapper.AccountMapper;
import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.repository.RoleRepository;
import com.ta2khu75.quiz.service.AccountService;
import com.ta2khu75.quiz.service.base.BaseService;
import com.ta2khu75.quiz.service.util.RedisUtil;
import com.ta2khu75.quiz.service.util.RedisUtil.NameModel;
import com.ta2khu75.quiz.util.FunctionUtil;
import com.ta2khu75.quiz.util.SecurityUtil;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl extends BaseService<AccountRepository, AccountMapper> implements AccountService {
	RoleRepository roleRepository;
	PasswordEncoder passwordEncoder;
	RedisUtil redisUtil;

	public AccountServiceImpl(AccountRepository repository, AccountMapper mapper, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, RedisUtil redisUtil) {
		super(repository, mapper);
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.redisUtil = redisUtil;
	}

	@Override
	public AccountResponse create(AccountRequest request) {
		if (request.getPassword().equals(request.getConfirmPassword())) {
			if (repository.existsByEmail(request.getEmail())) {
				throw new ExistingException("Email already exists");
			}
			Account account = mapper.toEntity(request);
			account.setEmail(account.getEmail().toLowerCase());
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			account.setRole(roleRepository.findByName("USER")
					.orElseThrow(() -> new NotFoundException("Could not find role with name: USER")));
			account.setEnabled(true);
			account.setDisplayName(account.getFirstName() + " " + account.getLastName());
			try {
				account = repository.save(account);
			} catch (DataIntegrityViolationException e) {
				throw new ExistingException("Email already exists");
			}
			return mapper.toResponse(account);
		}
		throw new NotMatchesException("password and confirm password not matches");
	}

	@Override
	public AccountDetailResponse read(String id) {
		return mapper.toDetailsResponse(repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found account with id: " + id)));
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

	@Override
	public ManagedAccountResponse updateStatus(String id, AccountStatusRequest request) {
		Account account = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Could not found account with id: " + id));
		mapper.update(request, account);
		if (!account.getRole().getId().equals(request.getRoleId())) {
			account.setRole(roleRepository.findById(request.getRoleId())
					.orElseThrow(() -> new NotFoundException("Could not found role with id: " + request.getRoleId())));
		}
		account = repository.save(account);
		if (account.isNonLocked()) {
			redisUtil.delete(NameModel.ACCOUNT, account.getEmail());
		} else {
			redisUtil.create(NameModel.ACCOUNT, account.getEmail(), account);
		}
		return mapper.toManagedResponse(account);
	}

	@Override
	public PageResponse<ManagedAccountResponse> readPage(String search, Pageable pageable) {
		PageResponse<ManagedAccountResponse> response = mapper
				.toPageResponse(repository.searchByDisplayNameOrEmail(search, pageable));
		response.setNumber(response.getNumber() + 1);
		return response;
	}

	@Override
	public AccountResponse updateInfo(AccountInfoRequest request) {
		String email = SecurityUtil.getCurrentUserLogin()
				.orElseThrow(() -> new NotFoundException("Could not find email"));
		Account account = repository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Could not find account with email: " + email));
		mapper.update(request, account);
		return mapper.toResponse(repository.save(account));
	}

//	@Override
//	public AccountDetailResponse readDetails(String id) {
//		Account account = repository.findById(id)
//				.orElseThrow(() -> new NotFoundException("Could not found account with id: " + id));
//		return mapper.toDetailsResponse(account);
//	}

	@Override
	public ManagedAccountResponse updateLock(String id) {
		Account account = FunctionUtil.findOrThrow(id, Account.class, repository::findById);
		account.setNonLocked(!account.isNonLocked());
		return mapper.toManagedResponse(repository.save(account));

	}

}
