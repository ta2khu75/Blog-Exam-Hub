package com.ta2khu75.quiz.entity.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountAuthResponse {
	String id;
	String username;
	String email;
	String role;
}
