package com.ta2khu75.quiz.entity.request.update;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountPasswordRequest {
	String password;
	String newPassword;
	String confirmPassword;
}
