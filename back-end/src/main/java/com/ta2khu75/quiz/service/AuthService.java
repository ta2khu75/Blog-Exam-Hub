package com.ta2khu75.quiz.service;

import com.ta2khu75.quiz.entity.request.AuthRequest;
import com.ta2khu75.quiz.entity.response.AccountResponse;
import com.ta2khu75.quiz.entity.response.AuthResponse;

public interface AuthService {
	AuthResponse login(AuthRequest authRequest);
	AccountResponse getAccount();
	AuthResponse refreshToken(String token);
	void logout();
}
