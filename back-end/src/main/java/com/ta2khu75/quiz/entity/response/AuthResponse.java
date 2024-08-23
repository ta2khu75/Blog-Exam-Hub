package com.ta2khu75.quiz.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	AccountResponse account;
	String accessToken;
	String refreshToken;
	boolean authenticated;
}
