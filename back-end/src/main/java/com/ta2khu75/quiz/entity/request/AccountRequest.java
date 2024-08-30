package com.ta2khu75.quiz.entity.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountRequest {

	@NotBlank(message = "Email must not be blank")
	@Email(message = "Email should be a valid email address")
	String email;

	@NotBlank(message = "First name must not be blank")
	String firstName;

	@NotBlank(message = "Last name must not be blank")
	String lastName;

	@NotBlank(message = "Password must not be blank")
	String password;

	@NotBlank(message = "Confirm password must not be blank")
	String confirmPassword;

	@NotNull(message = "Birthday must not be null")
	LocalDate birthday;

}
