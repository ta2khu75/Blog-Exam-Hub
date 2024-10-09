package com.ta2khu75.quiz.model.request.update;

import com.ta2khu75.quiz.model.base.AccountBase;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountInfoRequest extends AccountBase {
	@NotBlank(message = "Username must not be blank")
	String username;
}
