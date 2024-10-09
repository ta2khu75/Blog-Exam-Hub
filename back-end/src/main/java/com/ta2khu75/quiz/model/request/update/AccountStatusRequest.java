package com.ta2khu75.quiz.model.request.update;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountStatusRequest {
	@NotNull(message = "Enabled must not be null")
	Boolean enabled;
	@NotNull(message = "Non locked must not be null")
	Boolean nonLocked;
	@NotNull(message = "Role id must not be null")
	Long roleId;
}
