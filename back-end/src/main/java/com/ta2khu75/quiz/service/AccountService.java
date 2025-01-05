package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.model.request.update.AccountStatusRequest;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.AccountAuthDetailsResponse;
import com.ta2khu75.quiz.model.response.details.AccountDetailsResponse;

public interface AccountService extends BaseService<AccountRequest, AccountResponse, String>{
    AccountAuthDetailsResponse updateStatus(String id,AccountStatusRequest request);
    PageResponse<AccountAuthDetailsResponse> readPage(String search,Pageable pageable);
    AccountResponse updateInfo(AccountInfoRequest request);
    AccountDetailsResponse readDetails(String id);
    AccountAuthDetailsResponse updateLock(String id);
}
