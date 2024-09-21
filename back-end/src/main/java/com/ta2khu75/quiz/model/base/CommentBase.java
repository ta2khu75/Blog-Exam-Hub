package com.ta2khu75.quiz.model.base;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class CommentBase {
	@NotNull(message = "Content must not be null")
	String content;
}
