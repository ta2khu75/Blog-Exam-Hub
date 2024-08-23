package com.ta2khu75.quiz.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ta2khu75.quiz.entity.Account;
import com.ta2khu75.quiz.entity.request.AccountRequest;
import com.ta2khu75.quiz.entity.request.update.AccountPasswordRequest;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.entity.response.PageResponse;
import com.ta2khu75.quiz.exception.NotFoundException;
import com.ta2khu75.quiz.exception.NotMatchesException;
import com.ta2khu75.quiz.mapper.AccountMapper;
import com.ta2khu75.quiz.repository.AccountRepository;
import com.ta2khu75.quiz.service.AccountService;
import com.ta2khu75.quiz.util.SecurityUtil;
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepository repository;
    AccountMapper mapper;
    PasswordEncoder passwordEncoder;
    @Override
    public AccountResponse create(AccountRequest request) {
    	log.info("create account: {}",request);
        if(request.getPassword().equals(request.getConfirmPassword())){
                Account account =mapper.toEntity(request);
                account.setEmail(account.getEmail().toLowerCase());
                account.setPassword(passwordEncoder.encode(account.getPassword()));
                return mapper.toResponse(repository.save(account));
        }
        throw new NotMatchesException("password and confirm password not matches");
    }

    @Override
    public AccountResponse update(Long id, AccountRequest request) {
        if(request.getPassword().equals(request.getConfirmPassword())){
            Account account =repository.findById(id).orElseThrow(()-> new NotFoundException("Could not found account with id: "+id));
            mapper.update(request,account);
            return mapper.toResponse(repository.save(account));
        }
        throw new NotMatchesException("password and confirm password not matches");
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AccountResponse read(Long id) {
        return mapper.toResponse(repository.findById(id).orElseThrow(()-> new NotFoundException("Could not found account with id: "+id)));
    }

    @Override
    public PageResponse<AccountResponse> readPage(Pageable pageable) {
        return mapper.toPageResponse(repository.findAll(pageable).map((account)->mapper.toResponse(account)));
    }

	@Override
	public AccountResponse changePassword(AccountPasswordRequest request) {
		String email=SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new NotFoundException("Could not find email"));
		Account account=repository.findByEmail(email).orElseThrow(()->new NotFoundException("Could not find account with email: "+email));
		if(passwordEncoder.matches(request.password(), account.getPassword())){
			if(request.newPassword().equals(request.confirmPassword())) {
			account.setPassword(passwordEncoder.encode(request.newPassword()));
			return mapper.toResponse(repository.save(account));
			}
			throw new NotMatchesException("New password and confirm password not matches");
		}
		throw new NotMatchesException("Password not matches");	
	}
}
