package com.ta2khu75.quiz.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagedAccountResponse extends AccountResponse {
	boolean enabled;
	boolean nonLocked;
	RoleResponse role;
}
