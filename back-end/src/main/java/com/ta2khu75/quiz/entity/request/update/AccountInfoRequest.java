package com.ta2khu75.quiz.entity.request.update;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class AccountInfoRequest {
	@NotBlank
	String username;
	@NotBlank
	String firstName;
	@NotBlank
	String lastName;
	@NotNull
	LocalDate birthday;
}
