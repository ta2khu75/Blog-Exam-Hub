package com.ta2khu75.quiz.model.base;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class CommentBase {
	String content;
}
