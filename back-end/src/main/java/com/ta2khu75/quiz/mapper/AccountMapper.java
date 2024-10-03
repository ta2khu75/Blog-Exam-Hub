package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.model.entity.Account;
import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.model.request.update.AccountStatusRequest;
import com.ta2khu75.quiz.model.response.AccountAuthResponse;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.AccountDetailsResponse;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = InfoMapper.class)
public interface AccountMapper {
	@Mapping(target = "displayName", ignore = true)
	@Mapping(target = "blogs", ignore = true)
	@Mapping(target = "codeVerify", ignore = true)
	@Mapping(target = "comments", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "examResults", ignore = true)
	@Mapping(target = "exams", ignore = true)
	@Mapping(target = "nonLocked", ignore = true)
	@Mapping(target = "refreshToken", ignore = true)
	@Mapping(target = "role", ignore = true)
	Account toEntity(AccountRequest request);

	@Named("toAccountResponse")
	@Mapping(target = "info", source = "account", qualifiedByName = "toInfoResponse")
	@Mapping(target = "username", source = "displayName")
	AccountResponse toResponse(Account account);

	@Mapping(target = "info", source = "account", qualifiedByName = "toInfoResponse")
	@Mapping(target = "username", source = "displayName")
	AccountDetailsResponse toDetailsResponse(Account account);

	@Mapping(target = "role", source = "role.name")
	@Mapping(target = "username", source = "displayName")
	AccountAuthResponse toAuthResponse(Account account);

	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "blogs", ignore = true)
	@Mapping(target = "codeVerify", ignore = true)
	@Mapping(target = "comments", ignore = true)
	@Mapping(target = "displayName", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "examResults", ignore = true)
	@Mapping(target = "exams", ignore = true)
	@Mapping(target = "nonLocked", ignore = true)
	@Mapping(target = "refreshToken", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	void update(AccountRequest request, @MappingTarget Account account);

	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "blogs", ignore = true)
	@Mapping(target = "codeVerify", ignore = true)
	@Mapping(target = "comments", ignore = true)
	@Mapping(target = "displayName", source = "username")
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "examResults", ignore = true)
	@Mapping(target = "exams", ignore = true)
	@Mapping(target = "nonLocked", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "refreshToken", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	void update(AccountInfoRequest request, @MappingTarget Account account);

	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "birthday", ignore = true)
	@Mapping(target = "blogs", ignore = true)
	@Mapping(target = "codeVerify", ignore = true)
	@Mapping(target = "comments", ignore = true)
	@Mapping(target = "displayName", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "examResults", ignore = true)
	@Mapping(target = "exams", ignore = true)
	@Mapping(target = "firstName", ignore = true)
	@Mapping(target = "lastName", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "refreshToken", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	void update(AccountStatusRequest request, @MappingTarget Account account);

	PageResponse<AccountDetailsResponse> toPageResponse(Page<Account> response);
}
