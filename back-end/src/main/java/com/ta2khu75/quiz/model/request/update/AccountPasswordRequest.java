package com.ta2khu75.quiz.model.request.update;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountPasswordRequest {
	@NotBlank(message = "Password must not be blank")
	String password;
	@NotBlank(message = "New password must not be blank")
	String newPassword;
	@NotBlank(message = "Confirm new password must not be blank")
	String confirmPassword;
}
