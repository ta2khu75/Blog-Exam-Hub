package com.ta2khu75.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import com.ta2khu75.quiz.entity.Account;
import com.ta2khu75.quiz.entity.request.AccountRequest;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.entity.response.PageResponse;

@Mapper(componentModel = "spring")
public interface AccountMapper{
    Account toEntity(AccountRequest request);
    AccountResponse toResponse(Account account);
    void update(AccountRequest request, @MappingTarget Account account);
    PageResponse<AccountResponse> toPageResponse(Page<AccountResponse> response);
}
