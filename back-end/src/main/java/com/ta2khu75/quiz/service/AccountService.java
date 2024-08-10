package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.entity.request.AccountRequest;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.entity.response.PageResponse;

public interface AccountService {
    AccountResponse create(AccountRequest request);
    AccountResponse update(Long id,AccountRequest request);
    void delete(Long id);
    AccountResponse read(Long id);
    PageResponse<AccountResponse> readPage(Pageable pageable);

}
