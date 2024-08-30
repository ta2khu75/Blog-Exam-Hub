package com.ta2khu75.quiz.entity.request.update;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountStatusRequest {
	@NotNull
	Boolean enabled;
	@NotNull
	Boolean nonLocked;
}
