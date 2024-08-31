package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.entity.request.AccountRequest;
import com.ta2khu75.quiz.entity.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.entity.request.update.AccountPasswordRequest;
import com.ta2khu75.quiz.entity.request.update.AccountStatusRequest;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.entity.response.PageResponse;
import com.ta2khu75.quiz.entity.response.details.AccountDetailsResponse;

import jakarta.mail.MessagingException;

public interface AccountService {
    AccountResponse create(AccountRequest request) throws MessagingException;
    AccountDetailsResponse update(String id,AccountStatusRequest request);
    AccountResponse changePassword(AccountPasswordRequest request);
    void delete(String id);
    AccountResponse read(String id);
    PageResponse<AccountDetailsResponse> readPage(Pageable pageable);
    AccountResponse update(AccountInfoRequest request);
    boolean verify(String code);
    AccountResponse readMyAccount();
}
