package com.ta2khu75.quiz.model.request;

import com.ta2khu75.quiz.model.base.CommentBase;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequest extends CommentBase {
	String blogId;
}
