package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.entity.Account;
import com.ta2khu75.quiz.entity.request.AccountRequest;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.entity.response.PageResponse;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper{
    @Mapping(target = "examHistories", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "refreshToken", ignore = true)
	@Mapping(target = "role", ignore = true)
	Account toEntity(AccountRequest request);
    AccountResponse toResponse(Account account);
    @Mapping(target = "examHistories", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "refreshToken", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "authorities", ignore = true)
	void update(AccountRequest request, @MappingTarget Account account);
    PageResponse<AccountResponse> toPageResponse(Page<AccountResponse> response);
}
