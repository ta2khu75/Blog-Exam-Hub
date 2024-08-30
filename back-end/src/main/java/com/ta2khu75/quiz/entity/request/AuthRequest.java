package com.ta2khu75.quiz.entity.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequest {
	@NotBlank(message = "Email must not be blank")
	@Email(message = "Email should be a valid email address")
	String email;

	@NotBlank(message = "Password must not be blank")
	String password;
}