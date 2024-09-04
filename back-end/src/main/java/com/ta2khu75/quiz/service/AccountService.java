package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.model.request.update.AccountPasswordRequest;
//import com.ta2khu75.quiz.model.request.update.AccountPermissionRequest;
import com.ta2khu75.quiz.model.request.update.AccountStatusRequest;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.AccountDetailsResponse;

import jakarta.mail.MessagingException;

public interface AccountService {
    AccountResponse create(AccountRequest request) throws MessagingException;
    AccountDetailsResponse update(String id,AccountStatusRequest request);
    AccountResponse changePassword(AccountPasswordRequest request);
    void delete(String id);
    AccountResponse read(String id);
    PageResponse<AccountDetailsResponse> readPage(String search,Pageable pageable);
    AccountResponse update(AccountInfoRequest request);
    boolean verify(String code);
    AccountResponse readMyAccount();
//    AccountDetailsResponse update(String id,AccountPermissionRequest request);
}
