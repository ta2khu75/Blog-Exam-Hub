package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.entity.Account;
import com.ta2khu75.quiz.entity.request.AccountRequest;
import com.ta2khu75.quiz.entity.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.entity.request.update.AccountStatusRequest;
import com.ta2khu75.quiz.entity.response.AccountAuthResponse;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.entity.response.PageResponse;
import com.ta2khu75.quiz.entity.response.details.AccountDetailsResponse;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper{
	@Mapping(target = "displayName", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "codeVerify", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "examResults", ignore = true)
	@Mapping(target = "exams", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "nonLocked", ignore = true)
	@Mapping(target = "refreshToken", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	Account toEntity(AccountRequest request);
    @Mapping(target="username", source="displayName")
    AccountResponse toResponse(Account account);
	@Mapping(target = "displayName",ignore = true) 
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "codeVerify", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "examResults", ignore = true)
	@Mapping(target = "exams", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "nonLocked", ignore = true)
	@Mapping(target = "refreshToken", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	void update(AccountRequest request, @MappingTarget Account account);
	@Mapping(target = "displayName", source = "username")
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "codeVerify", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "examResults", ignore = true)
	@Mapping(target = "exams", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "nonLocked", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "refreshToken", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	void update(AccountInfoRequest request, @MappingTarget Account account);	
	@Mapping(target = "birthday", ignore = true)
	@Mapping(target = "codeVerify", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "displayName", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "examResults", ignore = true)
	@Mapping(target = "exams", ignore = true)
	@Mapping(target = "firstName", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "lastName", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "refreshToken", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	void update(AccountStatusRequest request, @MappingTarget Account account);
	@Mapping(target="role", source="role.name")
    @Mapping(target="username", source="displayName")
	AccountAuthResponse toAuthResponse(Account account);	
	@Mapping(target = "role", source = "role.name")
	@Mapping(target = "username", source = "displayName")
	AccountDetailsResponse toDetailsResponse(Account account);
    PageResponse<AccountDetailsResponse> toPageResponse(Page<AccountDetailsResponse> response);	
	}
