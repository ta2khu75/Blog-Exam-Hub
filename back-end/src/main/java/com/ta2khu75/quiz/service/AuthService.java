package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.model.request.AccountRequest;
import com.ta2khu75.quiz.model.request.AuthRequest;
import com.ta2khu75.quiz.model.request.update.AccountPasswordRequest;
import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.AuthResponse;

import jakarta.mail.MessagingException;

public interface AuthService {
	AuthResponse login(AuthRequest authRequest);

	AccountResponse register(AccountRequest accountRequest) throws MessagingException;

	AuthResponse refreshToken(String token);

	AccountResponse changePassword(AccountPasswordRequest request);

	boolean verify(String code);

	void logout();

	boolean checkAdmin();
}
