package com.ta2khu75.quiz.entity.response;

public record AuthResponse(AccountResponse account, String accessToken,	String refreshToken, boolean authenticated) {
}
