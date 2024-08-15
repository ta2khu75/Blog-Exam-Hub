package com.ta2khu75.quiz.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthResponse(AccountResponse account, @JsonProperty("access_token") String accessToken,
	@JsonProperty("refresh_token")	String refreshToken, boolean authenticated) {
}
