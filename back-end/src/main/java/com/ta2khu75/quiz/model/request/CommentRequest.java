package com.ta2khu75.quiz.model.request;

import com.ta2khu75.quiz.model.base.CommentBase;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequest extends CommentBase {
	@NotNull(message = "Blog Id must not be null")
	String blogId;
}
