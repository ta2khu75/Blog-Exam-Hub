package com.ta2khu75.quiz.model.response.details;

import com.ta2khu75.quiz.model.response.AccountResponse;
import com.ta2khu75.quiz.model.response.RoleResponse;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountAuthDetailsResponse extends AccountResponse {
	boolean enabled;
	boolean nonLocked;
	RoleResponse role;
}
