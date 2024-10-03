package com.ta2khu75.quiz.model.base;

import com.ta2khu75.quiz.model.AccessModifier;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class SearchRequestBase {
	String keyword;
	String authorId;
	String authorEmail;
	AccessModifier accessModifier;
	int page = 1;
	int size = 10;
}
