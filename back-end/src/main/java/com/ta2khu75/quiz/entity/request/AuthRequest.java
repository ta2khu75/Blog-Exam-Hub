package com.ta2khu75.quiz.entity.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class AuthRequest {
	@NotBlank
	@Email
	String email;
	@NotBlank
	String password;
}