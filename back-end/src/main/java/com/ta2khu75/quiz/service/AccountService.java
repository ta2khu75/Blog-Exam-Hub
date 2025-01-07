package com.ta2khu75.quiz.service;

import org.springframework.data.domain.Pageable;

import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.update.AccountInfoRequest;
import com.ta2khu75.quiz.model.request.update.AccountStatusRequest;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.ManagedAccountResponse;
import com.ta2khu75.quiz.model.response.PageResponse;
import com.ta2khu75.quiz.model.response.details.AccountDetailResponse;

public interface AccountService{
	AccountResponse create(AccountRequest request);
    AccountDetailResponse read(String id);
	void delete(String id);
    ManagedAccountResponse updateStatus(String id,AccountStatusRequest request);
    AccountResponse updateInfo(AccountInfoRequest request);
    PageResponse<ManagedAccountResponse> readPage(String search,Pageable pageable);
    ManagedAccountResponse updateLock(String id);
}
