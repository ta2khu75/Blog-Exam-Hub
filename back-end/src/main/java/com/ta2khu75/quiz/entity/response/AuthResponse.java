package com.ta2khu75.quiz.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthResponse(AccountResponse account, String accessToken,	String refreshToken, boolean authenticated) {
}
