package com.ta2khu75.quiz.model.base;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AccountBase {
	@NotBlank(message = "First name must not be blank")
	String firstName;

	@NotBlank(message = "Last name must not be blank")
	String lastName;

	@NotNull(message = "Birthday must not be null")
	LocalDate birthday;
}
